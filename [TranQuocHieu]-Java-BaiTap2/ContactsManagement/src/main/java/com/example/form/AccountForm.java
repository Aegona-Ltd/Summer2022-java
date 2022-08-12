package com.example.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AccountForm {
    private String email;
    private String password;
    private String messAccount;
    private String messEmail;
    private String messPassword;
}
