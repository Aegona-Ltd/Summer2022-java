package com.example.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AccountForm {
    @Email(message = "Invalid Email!!")
    @NotEmpty(message = "Please enter your Email!!")
    private String email;

    @NotEmpty(message = "Please enter your Password!!")
    private String password;
}
