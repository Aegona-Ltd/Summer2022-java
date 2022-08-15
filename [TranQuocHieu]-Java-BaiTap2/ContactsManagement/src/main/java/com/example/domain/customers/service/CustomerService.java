package com.example.domain.customers.service;


import com.example.domain.restResult.RestResult;
import com.example.form.AccountForm;
import org.springframework.validation.BindingResult;

public interface CustomerService {

    RestResult loginAccount(AccountForm account, BindingResult bindingResult);

    RestResult getAccountName();
}
