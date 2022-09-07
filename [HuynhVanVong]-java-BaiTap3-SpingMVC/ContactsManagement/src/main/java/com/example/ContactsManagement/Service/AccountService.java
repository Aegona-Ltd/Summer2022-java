package com.example.ContactsManagement.Service;

import com.example.ContactsManagement.Entity.Account;

public interface AccountService {
    Account findByEmail(String email);
    Account findByUserName(String username);

}
