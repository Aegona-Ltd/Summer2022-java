package com.example.domain.contacts.service;

import com.example.domain.contacts.document.Contact;
import com.example.domain.restresult.ResultList;
import com.example.domain.restresult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
import com.example.domain.restresult.RestResultError;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.validation.BindingResult;

import java.io.File;
import java.util.List;

public interface ContactService {

    ResultList listContact(int page, int size, String search);

    String getContact(int id) throws JsonProcessingException;

    RestResultError addContact(ContactDTO form, BindingResult bindingResult);

    RestResult deleteContact(Integer id);

    List<Contact> contactList(String sortBy);

    File getFileById(Integer id);

    void addFileNameById(Integer id, String filename);

    List<Contact> contactListByEmail(String search);
}
