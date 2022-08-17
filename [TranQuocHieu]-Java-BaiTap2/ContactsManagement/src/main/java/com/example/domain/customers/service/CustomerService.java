package com.example.domain.customers.service;


import com.example.domain.restResult.RestResult;
import com.example.domain.customers.model.AccountDTO;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

public interface CustomerService {

    RestResult loginAccount(AccountDTO account, BindingResult bindingResult);

    RestResult getAccountName(HttpServletRequest request);
}
