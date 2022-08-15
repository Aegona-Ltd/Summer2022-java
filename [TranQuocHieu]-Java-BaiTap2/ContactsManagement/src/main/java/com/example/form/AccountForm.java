package com.example.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AccountForm {
    @Email(message = "Email khong hop le!!")
    @NotEmpty(message = "Vui long nhap Email!!")
    private String email;

    @NotEmpty(message = "Vui long nhap mat khau!!")
    private String password;
}
