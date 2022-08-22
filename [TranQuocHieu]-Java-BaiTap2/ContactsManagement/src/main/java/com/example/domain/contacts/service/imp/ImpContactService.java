package com.example.domain.contacts.service.imp;

import com.example.domain.contacts.model.Contact;
import com.example.domain.contacts.model.result.ResultContact;
import com.example.domain.contacts.model.result.ResultContactList;
import com.example.domain.contacts.service.ContactService;
import com.example.domain.restresult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
import com.example.domain.restresult.RestResultError;
import com.example.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    @Override
    public ResultContactList listContact(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("datatime").descending());

        ResultContactList result = new ResultContactList();
        result.setResult(0);
        result.setMessage("Success");
        result.setPage(page+1);
        result.setData(repository.findAll(pageable));

        return result;
    }

    @Override
    public ResultContact getContact(int id) {
        Contact contact = repository.findById(id).orElse(null);
        ResultContact result = new ResultContact();

        if (contact==null) {
            result.setResult(10);
            result.setMessage("Not Found Contact by id: " + id);
        }else {
            result.setResult(0);
            result.setMessage("Success");
            result.setData(contact);
        }
        return result;
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
