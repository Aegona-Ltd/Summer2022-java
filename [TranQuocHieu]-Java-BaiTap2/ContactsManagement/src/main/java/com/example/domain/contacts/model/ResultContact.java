package com.example.domain.contacts.model;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class ResultContact {
    private Integer page;
    private Page<Contact> data;
}
