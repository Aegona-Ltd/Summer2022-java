package com.example.domain.contacts.service.imp;

import com.example.domain.contacts.model.Contact;
import com.example.domain.contacts.model.result.ContactSerializer;
import com.example.domain.contacts.service.ContactService;
import com.example.domain.restresult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
import com.example.domain.restresult.RestResultError;
import com.example.domain.restresult.ResultList;
import com.example.domain.restresult.ResultMapper;
import com.example.repository.ContactsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
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
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        Page<Contact> contacts = repository.findAll(pageable);
        ResultList result = ResultMapper.pageableToResultList(contacts);
        result.setSort(sort);
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

    /*
     * Method check account on database
     * Method return RestResult:
     *   result 0: login success
     *   result 90: Wrong input value
     * */
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
            result.setResult(90);
            result.setMessage("Valid input");
            result.setError(errors);
            return result;
        }
        try{
            Integer phonenumber = Integer.parseInt(form.getPhone());
        }catch (Exception e){
            result.setResult(90);
            errors.put("phone", "Invalid Phone number");
            result.setError(errors);
            return result;
        }

        LocalDateTime myDateObj = LocalDateTime.now();

        Contact contact = new Contact(form);
        contact.setDatatime(myDateObj);
        repository.save(contact);
        result.setResult(0);
        result.setMessage("Success");
        return result;
    }

    @Override
    public RestResult deleteContact(Integer id) {
        repository.deleteById(id);
        RestResult result = new RestResult();
        result.setResult(0);
        result.setMessage("Success");
        return result;
    }
}
