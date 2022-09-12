package com.example.ContactsManagement.Controller;

import com.example.ContactsManagement.Entity.Account;
import com.example.ContactsManagement.Entity.Contact;
import com.example.ContactsManagement.Repository.ContactReposistory2;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
public class ContactRestController2 {
    @Autowired
    ContactReposistory2 controller2;
    @Autowired
    EntityManager entityManager;


    @RequestMapping(value = "/table/contacts", method = RequestMethod.GET)
    public DataTablesOutput<Contact> list(@Valid DataTablesInput input) {
//        input.setLength(3);
        //input.setStart(2);
        return controller2.findAll(input);
    }

//    @RequestMapping(value = "/table/contacts", method = RequestMethod.POST)
//    public DataTablesOutput<Account> listPOST(@Valid @RequestBody DataTablesInput input) {
//        return controller2.findAll(input);
//    }

}
