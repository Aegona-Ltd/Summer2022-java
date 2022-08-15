package com.example.domain.contacts.service.imp;

import com.example.domain.contacts.model.Contact;
import com.example.domain.contacts.service.ContactService;
import com.example.domain.restResult.RestResult;
import com.example.form.ContactForm;
import com.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    private ContactRepository repository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public RestResult listContact() {

        RestResult result = new RestResult();
        result.setResult(0);

        result.setData(repository.findAll());

        return result;
    }

    /*
    *
    * */
    @Override
    public RestResult addContact(@Valid ContactForm form, BindingResult bindingResult) {
        RestResult result = new RestResult();
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error: bindingResult.getFieldErrors()){
                System.out.println(error);
                String mess = messageSource.getMessage(error, null);
                errors.put(error.getField(), mess);
            }
            result.setResult(90);
            result.setErrors(errors);
            return result;
        }

        try{
            Integer phonenumber = Integer.parseInt(form.getPhone());
        }catch (Exception e){
            Map<String, String> errors = new HashMap<>();
            result.setResult(90);
            errors.put("phone", "Invalid Phone number");
            result.setErrors(errors);
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
        return result;
    }
}
