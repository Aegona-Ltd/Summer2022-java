package com.example.controller.login;

import com.example.domain.customers.service.CustomerService;
import com.example.domain.restResult.RestResult;
import com.example.domain.customers.model.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class LoginRestController {

    @Autowired
    private CustomerService service;

    @PostMapping("")
    public RestResult login(@RequestBody @Valid AccountDTO form, BindingResult bindingResult, HttpServletResponse response) {
        RestResult result = service.loginAccount(form, bindingResult);
        if (result.getResult()==0){
            Cookie cookie = new Cookie("username", form.getEmail());
            response.addCookie(cookie);
        }
        return result;
    }

    @GetMapping("/account")
    public RestResult accountName(HttpServletRequest request) {
        return service.getAccountName(request);
    }
}
