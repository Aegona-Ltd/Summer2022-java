package com.example.domain.contacts.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Document(collection = "contacts")
public class Contact implements Serializable {

    @Id
    private Integer id;
    private String dateTime;
    private String fullname;
    private String email;
    private String phone;
    private String subject;
    private String message;
    private String fileName;
    private Boolean seen;
}
