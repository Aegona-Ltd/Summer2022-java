package com.example.domain.contacts.service;

import com.example.domain.restresult.ResultList;
import com.example.domain.restresult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
import com.example.domain.restresult.RestResultError;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.validation.BindingResult;

public interface ContactService {

    ResultList listContact(int page, int size);

    String getContact(int id) throws JsonProcessingException;

    RestResultError addContact(ContactDTO form, BindingResult bindingResult);

    RestResult deleteContact(Integer id);
}
