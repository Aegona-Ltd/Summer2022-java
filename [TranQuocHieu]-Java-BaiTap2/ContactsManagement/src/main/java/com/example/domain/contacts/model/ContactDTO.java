package com.example.domain.contacts.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.File;

@Data
public class ContactDTO {
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

    private String keyRecapcha;
}
