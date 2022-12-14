package com.example.domain.contacts.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "datetime")
    private LocalDateTime datetime;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "subject")
    private String subject;
    @Column(name = "message")
    private String message;

    @Column(name = "file_name")
    private String fileName;

    public Contact(){}

    public Contact (ContactDTO contactForm){
        this.id = 0;
        this.fullname = contactForm.getFullname();
        this.email = contactForm.getEmail();
        this.phone = contactForm.getPhone();
        this.subject = contactForm.getSubject();
        this.message = contactForm.getMess();
    }
}
