package com.example.domain.customers.service.imp;

import com.example.domain.customers.model.Customer;
import com.example.domain.customers.service.CustomerService;
import com.example.form.AccountForm;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpCustomerService implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public AccountForm loginAccount(AccountForm account) {
        String regex = "^(.+)@(.+)$";
        String email = account.getEmail();
        String pass = account.getPassword();
        boolean success = true;
//        Validation Email
        if (email.length()==0) {
            account.setMessEmail("Email is not null!!");
            success = false;
        }else if (!email.matches(regex)){
            account.setMessEmail("Invalid email!!");
            success = false;
        }else {
            account.setMessEmail("");
        }
//        Validation Password
        if (pass.length()==0) {
            account.setMessPassword("Password is not null");
            success = false;
        }else {
            account.setMessPassword("");
        }
//        Validation Account DB
        if (success==true) {
            Customer customer = repository.findById(account.getEmail()).orElse(null);
            if (customer==null) {
                account.setMessAccount("Wrong Email");
            }else if (customer.getPassword().equals(account.getPassword())) {
                return null;
            }else {
                account.setMessAccount("Wrong Password");
            }
        }
        return account;
    }
}
