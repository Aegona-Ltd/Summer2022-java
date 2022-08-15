package com.example.domain.customers.service.imp;

import com.example.domain.customers.model.Customer;
import com.example.domain.customers.service.CustomerService;
import com.example.domain.restResult.RestResult;
import com.example.form.AccountForm;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImpCustomerService implements CustomerService {

    private static String accountName;
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
    public RestResult loginAccount(@Valid AccountForm account, BindingResult bindingResult) {
        System.out.println(account);
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
        Customer customer = repository.findById(account.getEmail()).orElse(null);

        if (customer==null) {
            result.setResult(10);
            result.setMessage("Wrong email!!");
        }else if (!customer.getPassword().equals(account.getPassword())) {
            result.setResult(20);
            result.setMessage("Wrong password!!");
        }else {
            result.setResult(0);
            result.setMessage("Success");
            this.accountName = customer.getEmail();
        }
        return result;
    }

    public RestResult getAccountName() {
        RestResult result = new RestResult();
        result.setResult(0);
        result.setMessage(this.accountName);
        return result;
    }
}
