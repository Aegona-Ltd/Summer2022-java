package com.example.domain.contacts.service;

import com.example.domain.contacts.model.Contact;
import com.example.form.ContactForm;

import java.util.List;

public interface ContactService {

    List<Contact> listContact();
    ContactForm addContact(ContactForm form);

    void deleteContact(Integer id);
}
