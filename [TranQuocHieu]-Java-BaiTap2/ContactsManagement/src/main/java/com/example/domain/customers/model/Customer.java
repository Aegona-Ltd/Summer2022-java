package com.example.domain.customers.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
