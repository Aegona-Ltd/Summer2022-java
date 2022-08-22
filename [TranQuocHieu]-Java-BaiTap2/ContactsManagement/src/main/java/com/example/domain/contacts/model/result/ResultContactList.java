package com.example.domain.contacts.model.result;

import com.example.domain.contacts.model.Contact;
import com.example.domain.restresult.RestResult;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class ResultContactList extends RestResult {
    private Integer page;
    private Page<Contact> data;
}
