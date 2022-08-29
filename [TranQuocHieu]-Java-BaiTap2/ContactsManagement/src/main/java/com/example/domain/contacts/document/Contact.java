package com.example.domain.contacts.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Data
@Document(collection = "contacts")
public class Contact {

    @Id
    private Integer id;
    private String dateTime;
    private String fullname;
    private String email;
    private String phone;
    private String subject;
    private String message;
    private String fileName;
}
