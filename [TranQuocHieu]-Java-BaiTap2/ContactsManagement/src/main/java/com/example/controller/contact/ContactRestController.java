package com.example.controller.contact;

import com.example.domain.contacts.service.ContactService;
import com.example.domain.restresult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ContactRestController {

    @Autowired
    private ContactService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/contact")
    public ResponseEntity<?> listContact(@RequestParam (value = "page",defaultValue = "1",required = false) Integer page,
                                                         @RequestParam (value = "size",defaultValue = "5",required = false) Integer size) {
        return ResponseEntity.ok().body(service.listContact(page-1 , size));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/contact/{id:.+}")
    public ResponseEntity<?> getContact(@PathVariable Integer id) throws JsonProcessingException {
        return ResponseEntity.ok().body(service.getContact(id));
    }

    @PostMapping("/auth/contact")
    public ResponseEntity<?> addContact(@RequestBody @Valid ContactDTO form, BindingResult bindingResult) {
        return ResponseEntity.ok().body(service.addContact(form, bindingResult));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/contact/{id:.+}")
    public ResponseEntity<RestResult> deleteContact(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.deleteContact(id));
    }
}
