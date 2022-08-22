package com.example.domain.contacts.model.result;

import com.example.domain.contacts.model.Contact;
import com.example.domain.restresult.RestResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultContact {
    private Integer result;
    private String message;
    private Contact data;
}
