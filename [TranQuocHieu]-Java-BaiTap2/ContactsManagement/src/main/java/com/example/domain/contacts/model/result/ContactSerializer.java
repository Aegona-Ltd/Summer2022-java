package com.example.domain.contacts.model.result;

import com.example.domain.contacts.document.Contact;
import lombok.Data;

@Data
public class ContactSerializer {
    private Integer result;
    private String message;
    private Contact contact;
}
