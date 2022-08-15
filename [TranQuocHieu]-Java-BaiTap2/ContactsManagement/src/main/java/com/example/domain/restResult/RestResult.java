package com.example.domain.restResult;

import com.example.domain.contacts.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResult {

        private int result;
        private String message;
        private Map<String, String> errors;
        private List<Contact> data;

}
