package com.example.controller;

import com.example.domain.contacts.service.ContactService;
import com.example.form.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contactus")
public class ContactFormController {

    @Autowired
    private ContactService service;

    @GetMapping("")
    public String viewContact(@ModelAttribute ContactForm form) {
        return "contactForm";
    }
    @PostMapping("")
    public String contactUs(ContactForm form) {
        form = service.addContact(form);
        return (form==null) ? "redirect:/contacts":viewContact(form);
    }
}
