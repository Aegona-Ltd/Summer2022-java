package com.example.ContactsManagement.ServiceImpl;

import com.example.ContactsManagement.Entity.Account;
import com.example.ContactsManagement.Repository.AccountReposistory;
import com.example.ContactsManagement.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountReposistory AcccountReposistory;



    @Override
    public Account findByEmail(String email) {
        Account user = new Account();
        user = AcccountReposistory.findByEmail(email);
        return user;
    }

    @Override
    public Account findByUserName(String username) {
        return AcccountReposistory.findByUserNameEquals(username);
    }
}
