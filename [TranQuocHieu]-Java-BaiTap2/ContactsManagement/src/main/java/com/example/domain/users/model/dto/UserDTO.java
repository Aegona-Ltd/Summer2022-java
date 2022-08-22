package com.example.domain.users.model.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDTO {


    @Email(message = "Invalid Email!!")
    @NotEmpty(message = "Please enter your Email!!")
    private String email;

    @NotEmpty(message = "Please enter your Password!!")
    private String password;
}
