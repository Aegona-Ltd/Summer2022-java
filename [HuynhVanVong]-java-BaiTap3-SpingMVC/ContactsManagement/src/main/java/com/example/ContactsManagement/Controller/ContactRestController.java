package com.example.ContactsManagement.Controller;

import com.example.ContactsManagement.DTO.ContactDTO;
import com.example.ContactsManagement.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactRestController {
    @Autowired
    ContactService contactService;

    @PostMapping()
    public ContactDTO saveNewContact(@RequestBody ContactDTO contactDTO) {
        return contactService.addContact(contactDTO);
    }
    @GetMapping()
    public List<ContactDTO> getContacts() {
        return contactService.getAllContacts();
    }
}
