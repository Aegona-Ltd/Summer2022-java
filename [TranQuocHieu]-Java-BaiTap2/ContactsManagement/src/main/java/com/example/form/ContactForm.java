package com.example.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ContactForm {
    @NotEmpty(message = "Please enter your Fullname")
    private String fullname;

    @Email(message = "Invalid Email!!")
    @NotEmpty(message = "Please enter your Email!!")
    private String email;

    @NotEmpty(message = "Please enter your Phone Number")
    @Length(min = 10, max = 10, message = "Invalid Phone Number!!")
    private String phone;

    @NotEmpty(message = "Please enter your Subject")
    private String subject;

    @NotEmpty(message = "Please enter your Message")
    private String mess;
}
