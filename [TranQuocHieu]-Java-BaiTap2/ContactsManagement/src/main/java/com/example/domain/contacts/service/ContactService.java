package com.example.domain.contacts.service;

import com.example.domain.contacts.model.ResultContact;
import com.example.domain.restResult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
import org.springframework.validation.BindingResult;

public interface ContactService {

    ResultContact listContact(int page);

    RestResult getContact(int id);

    RestResult addContact(ContactDTO form, BindingResult bindingResult);

    RestResult deleteContact(Integer id);
}
