package com.example.domain.users.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserDTO {

    @NotEmpty(message = "Please enter your Name!!")
    private String name;

    @Email(message = "Invalid Email!!")
    @NotEmpty(message = "Please enter your Email!!")
    private String email;

    @NotEmpty(message = "Please enter your Password!!")
    private String password;

    @NotEmpty(message = "Please enter your Password Confirm!!")
    private String comfirm;
}
