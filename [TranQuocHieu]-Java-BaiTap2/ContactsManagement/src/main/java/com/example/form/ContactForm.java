package com.example.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ContactForm {
    @NotEmpty(message = "Vui long nhap full name")
    private String fullname;

    @Email(message = "Email khong hop le!!")
    @NotEmpty(message = "Vui long nhap Email!!")
    private String email;

    @NotEmpty(message = "Vui long nhap Phone number")
    @Length(min = 10, max = 10, message = "Phone number khong hop le!!")
    private String phone;

    @NotEmpty(message = "Vui long nhap Subject")
    private String subject;

    @NotEmpty(message = "Vui long nhap Message")
    private String mess;
}
