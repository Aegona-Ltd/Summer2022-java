package com.example.ContactsManagement.ServiceImpl;

import com.example.ContactsManagement.DTO.ContactDTO;
import com.example.ContactsManagement.Entity.Contact;
import com.example.ContactsManagement.Repository.ContactReposistory;
import com.example.ContactsManagement.Service.ContactService;
import com.example.ContactsManagement.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactReposistory contactReposistory;
    @Autowired
    Convert convert;
    @Override
    public List<Contact> getAllContacts() {
        return null;
    }

    @Override
    public ContactDTO addContact(ContactDTO contactDTO) {
        Contact newContact = new Contact();
        newContact.setDate(LocalDateTime.now());
        newContact = convert.toEntity(contactDTO, Contact.class);
        newContact = contactReposistory.save(newContact);
        return convert.toDto(newContact, ContactDTO.class);
    }
}
