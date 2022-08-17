package com.example.controller.contact;

import com.example.domain.contacts.model.ResultContact;
import com.example.domain.contacts.service.ContactService;
import com.example.domain.restResult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
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
    public ResultContact listContact(@RequestParam (value = "page",defaultValue = "1",required = false) Integer page) {
        return service.listContact(page-1);
    }

    @GetMapping("/{id:.+}")
    public RestResult getContact(@PathVariable Integer id) {
        return service.getContact(id);
    }

    @PostMapping("")
    public RestResult addContact(@RequestBody @Valid ContactDTO form, BindingResult bindingResult) {
        return service.addContact(form, bindingResult);
    }

    @DeleteMapping("/{id:.+}")
    public RestResult deleteContact(@PathVariable Integer id) {
        return service.deleteContact(id);
    }
}
