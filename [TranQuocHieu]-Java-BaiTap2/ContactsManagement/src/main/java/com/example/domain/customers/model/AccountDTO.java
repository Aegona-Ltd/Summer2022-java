package com.example.domain.customers.model;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AccountDTO {
    @Email(message = "Invalid Email!!")
    @NotEmpty(message = "Please enter your Email!!")
    private String email;

    @NotEmpty(message = "Please enter your Password!!")
    private String password;
}
