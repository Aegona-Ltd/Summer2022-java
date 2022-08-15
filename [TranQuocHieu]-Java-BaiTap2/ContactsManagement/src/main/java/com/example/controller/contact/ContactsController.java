package com.example.controller.contact;

import com.example.domain.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactService service;

    @GetMapping("")
    public String viewContactList() {
        return "contacts";
    }
}
