package com.example.controller.contact;

import com.example.domain.contacts.service.ContactService;
import com.example.domain.restResult.RestResult;
import com.example.form.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/contact")
public class ContactRestController {

    @Autowired
    private ContactService service;


    @GetMapping("")
    public RestResult listContact() {
        return service.listContact();
    }

    @PostMapping("")
    public RestResult addContact(@RequestBody @Valid ContactForm form, BindingResult bindingResult) {
        return service.addContact(form, bindingResult);
    }
}
