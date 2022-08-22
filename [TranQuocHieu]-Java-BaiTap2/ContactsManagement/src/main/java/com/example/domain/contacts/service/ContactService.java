package com.example.domain.contacts.service;

import com.example.domain.contacts.model.result.ResultContact;
import com.example.domain.contacts.model.result.ResultContactList;
import com.example.domain.restresult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
import com.example.domain.restresult.RestResultError;
import org.springframework.validation.BindingResult;

public interface ContactService {

    ResultContactList listContact(int page, int size);

    ResultContact getContact(int id);

    RestResultError addContact(ContactDTO form, BindingResult bindingResult);

    RestResult deleteContact(Integer id);
}
