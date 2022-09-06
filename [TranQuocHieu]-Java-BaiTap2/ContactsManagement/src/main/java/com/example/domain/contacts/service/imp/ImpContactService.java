package com.example.domain.contacts.service.imp;

import com.example.domain.contacts.document.Contact;
import com.example.domain.contacts.model.result.ContactSerializer;
import com.example.domain.contacts.service.ContactService;
import com.example.domain.restresult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
import com.example.domain.restresult.RestResultError;
import com.example.domain.restresult.ResultList;
import com.example.repository.ContactsMongoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ImpContactService implements ContactService {

    public static final String HASH_KEY = "contacts";

    @Autowired
    private RedisTemplate template;

    @Autowired
    private ContactsMongoRepository repository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${redis.expire}")
    private Integer redisExpire;

    @Override
    public ResultList listContact(int page, int size, String search) {
        String sort = "_id";
        List<Contact> contactList = (search.equals("")) ? contactList(sort): contactListByEmail(search);
        Collections.sort(contactList, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o2.getId().compareTo(o1.getId());
            }
        });
        List<Contact> contactsResult = new ArrayList<>();
        int index = (page-1)*size;
        int count = index+size;
        while (index < contactList.size() && index < count){
            contactsResult.add(contactList.get(index));
            index++;
        }
        int totalPage = contactList.size()/size;
        int totalPageResult = (contactList.size()%size==0) ? totalPage: totalPage +1;

        ResultList result = new ResultList();
        result.setResult(0);
        result.setPage(page);
        result.setSize(size);
        result.setTotalElements((long) contactList.size());
        result.setTotalPages(totalPageResult);
        result.setSort(sort);
        result.setData(contactsResult);
        return result;
    }

    @Override
    public String getContact(int id) throws JsonProcessingException {

        ContactSerializer contactSerializer = new ContactSerializer();
        Contact contact = getContactFromCache(id);
        if (contact==null) {
            contactSerializer.setResult(10);
            contactSerializer.setMessage("Not Found Contact by id: " + id);
        }else {
            contactSerializer.setResult(0);
            contactSerializer.setMessage("Success");
            if (!contact.getSeen()) {
                contact.setSeen(true);
                contact = repository.save(contact);
                if (template.hasKey(HASH_KEY)) addcontact(contact);
            }
            contactSerializer.setContact(contact);
        }
        return objectMapper.writeValueAsString(contactSerializer);
    }

    @Override
    public RestResultError addContact(@Valid ContactDTO form, BindingResult bindingResult) {
        RestResultError result = new RestResultError();
        Map<String, String> errors = new HashMap<>();
        if (bindingResult.hasErrors()) {
            for (FieldError error: bindingResult.getFieldErrors()){
                System.out.println(error);
                String mess = messageSource.getMessage(error, null);
                errors.put(error.getField(), mess);
            }
            result.setResult(-1);
            result.setMessage("Valid input");
            result.setError(errors);
            return result;
        }
        try{
            Integer phonenumber = Integer.parseInt(form.getPhone());
        }catch (Exception e){
            result.setResult(-1);
            errors.put("phone", "Invalid Phone number");
            result.setError(errors);
            return result;
        }

        List<Contact> contactDB = repository.findAll(Sort.by("_id").descending());
        int idContact = 1;
        if (contactDB.size() > 0)  idContact = contactDB.get(0).getId()+1;

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Contact contact = new Contact();
        contact.setId(idContact);
        contact.setFullname(form.getFullname());
        contact.setEmail(form.getEmail());
        contact.setPhone(form.getPhone());
        contact.setSubject(form.getSubject());
        contact.setMessage(form.getMess());
        contact.setDateTime(myDateObj.format(formatter));
        contact.setSeen(false);
        contact = repository.save(contact);
        if (template.hasKey(HASH_KEY)) {
            template.delete(HASH_KEY);
            contactList("_id");
        }
        result.setResult(contact.getId());
        result.setMessage("Success");
        return result;
    }

    @Override
    public RestResult deleteContact(Integer id) {
        Contact contact = getContactFromCache(id);
        if (contact.getFileName()!=null) {
            File file = getFileById(contact.getId());
            file.deleteOnExit();
        }
        template.opsForHash().delete(HASH_KEY, id);
        repository.deleteById(id);
        RestResult result = new RestResult();
        result.setResult(0);
        result.setMessage("Success");
        return result;
    }

    @Override
    public List<Contact> contactList(String sortBy) {
        if (!template.hasKey(HASH_KEY)) {
            List<Contact> contacts = repository.findAll(Sort.by(sortBy).descending());
            for (Contact contact: contacts) {
                addcontact(contact);
            }
            template.expire(HASH_KEY, Duration.ofSeconds(redisExpire));
        }
        return template.opsForHash().values(HASH_KEY);
    }


    @Override
    public File getFileById(Integer id) {
        String filename = getContactFromCache(id).getFileName();
        return new File("src/main/resources/file/upload/"+filename);
    }

    @Override
    public void addFileNameById(Integer id, String filename) {
        Contact contactDB = repository.findById(id).orElse(null);
        Contact contactCache = getContactFromCache(id);
        contactCache.setFileName(filename);
        contactDB.setFileName(filename);

        addcontact(contactCache);
        repository.save(contactDB);
    }

    @Override
    public List<Contact> contactListByEmail(String search) {
        return repository.findByEmail(search);
    }

    @Override
    public Contact addcontact(Contact contact) {
        template.opsForHash().put(HASH_KEY, contact.getId(), contact);
        System.out.println(contact);
        return contact;
    }

    @Override
    public Contact getContactFromCache(Integer id) {
        Contact contact = (Contact) template.opsForHash().get(HASH_KEY, id);
        if (contact==null) {
            contact = repository.findById(id).orElse(null);
            if (contact!=null) {
                addcontact(contact);
                return contact;
            }
            return null;
        }
        return contact;
    }

}
