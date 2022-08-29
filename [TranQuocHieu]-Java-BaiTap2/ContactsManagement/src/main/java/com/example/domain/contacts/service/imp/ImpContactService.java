package com.example.domain.contacts.service.imp;

import com.example.domain.contacts.model.Contact;
import com.example.domain.contacts.model.result.ContactSerializer;
import com.example.domain.contacts.service.ContactService;
import com.example.domain.restresult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
import com.example.domain.restresult.RestResultError;
import com.example.domain.restresult.ResultList;
import com.example.repository.ContactsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImpContactService implements ContactService {

    @Autowired
    private ContactsRepository repository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResultList listContact(int page, int size) {
        String sort = "datatime";
        List<Contact> contactList = contactList(sort);
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
        Contact contact = repository.findById(id).orElse(null);

        if (contact==null) {
            contactSerializer.setResult(10);
            contactSerializer.setMessage("Not Found Contact by id: " + id);
        }else {
            contactSerializer.setResult(0);
            contactSerializer.setMessage("Success");
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

        LocalDateTime myDateObj = LocalDateTime.now();

        Contact contact = new Contact(form);
        contact.setDatatime(myDateObj);
        contact = repository.save(contact);
        result.setResult(contact.getId());
        result.setMessage("Success");
        return result;
    }

    @Override
    public RestResult deleteContact(Integer id) {
        Contact contact = repository.findById(id).orElse(null);
        if (contact.getFileName()!=null) {
            File file = getFileById(contact.getId());
            file.deleteOnExit();
        }
        repository.deleteById(id);
        RestResult result = new RestResult();
        result.setResult(0);
        result.setMessage("Success");
        return result;
    }

    @Override
    public List<Contact> contactList(String sortBy) {
        return repository.findAll(Sort.by(sortBy).descending());
    }

    @Override
    public File getFileById(Integer id) {
        String filename = repository.findById(id).orElse(null).getFileName();
        return new File("src/main/resources/file/upload/"+filename);
    }

    @Override
    public void addFileNameById(Integer id, String filename) {
        Contact contact = repository.findById(id).orElse(null);
        contact.setFileName(filename);
        repository.save(contact);
    }
}
