package com.example.domain.contacts.service;

import com.example.domain.contacts.model.Contact;
import com.example.domain.restResult.RestResult;
import com.example.form.ContactForm;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ContactService {

    RestResult listContact();

    RestResult getContact(int id);

    RestResult addContact(ContactForm form, BindingResult bindingResult);

    RestResult deleteContact(Integer id);
}
