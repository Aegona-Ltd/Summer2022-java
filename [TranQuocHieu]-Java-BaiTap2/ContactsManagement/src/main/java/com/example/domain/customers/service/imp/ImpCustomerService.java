package com.example.domain.customers.service.imp;

import com.example.domain.customers.model.Account;
import com.example.domain.customers.service.CustomerService;
import com.example.domain.restResult.RestResult;
import com.example.domain.customers.model.AccountDTO;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImpCustomerService implements CustomerService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CustomerRepository repository;

    /*
    * Method check account on database
    * Method return RestResult:
    *   result 0: login success
    *   result 10: Wrong Email
    *   result 20: Wrong Password
    *   result 90: Wrong input value
    * */
    @Override
    public RestResult loginAccount(@Valid AccountDTO account, BindingResult bindingResult) {
        RestResult result = new RestResult();
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error: bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, null);
                errors.put(error.getField(), message);
            }
            result.setResult(90);
            result.setMessage("Wrong input value");
            result.setErrors(errors);
            return result;
        }

//        Get account in database
        Account customer = repository.findById(account.getEmail()).orElse(null);

        if (customer==null) {
            result.setResult(10);
            result.setMessage("Wrong email!!");
        }else if (!customer.getPassword().equals(account.getPassword())) {
            result.setResult(20);
            result.setMessage("Wrong password!!");
        }else {
            result.setResult(0);
            result.setMessage("Success");
        }
        return result;
    }

    public RestResult getAccountName(HttpServletRequest request) {
        RestResult result = new RestResult();
        result.setResult(0);
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        if (cookies==null) result.setMessage("");
        else {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("username")) {
                    result.setMessage(cookies[i].getValue());
                }
            }
        }
        return result;
    }
}
