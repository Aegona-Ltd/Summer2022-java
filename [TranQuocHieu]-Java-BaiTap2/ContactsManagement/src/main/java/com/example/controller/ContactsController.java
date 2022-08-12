package com.example.controller;

import com.example.domain.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactService service;

    @GetMapping("")
    public String viewContactList(Model model) {
        model.addAttribute("contactList", service.listContact());
        return "contacts";
    }

    @GetMapping("/delete/{id:.+}")
    public String deleteContact(@PathVariable("id") Integer id) {
        service.deleteContact(id);
        return "redirect:/contacts";
    }
}
