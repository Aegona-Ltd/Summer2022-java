package com.example.domain.customers.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
