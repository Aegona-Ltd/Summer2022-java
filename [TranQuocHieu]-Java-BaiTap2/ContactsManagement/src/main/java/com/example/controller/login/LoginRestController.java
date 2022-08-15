package com.example.controller.login;

import com.example.domain.customers.service.CustomerService;
import com.example.domain.restResult.RestResult;
import com.example.form.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class LoginRestController {

    @Autowired
    private CustomerService service;

    @PostMapping("")
    public RestResult login(@RequestBody @Valid AccountForm form, BindingResult bindingResult) {
        return service.loginAccount(form, bindingResult);
    }

    @GetMapping("/account")
    public RestResult accountName() {
        return service.getAccountName();
    }
}
