package com.example.ContactsManagement.Service;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.DTO.Output.AccountOutput;
import com.example.ContactsManagement.Entity.Account;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account findByUserName(String username);
    Account findById(Integer id);
    List<AccountDTO> getAllAccounts();
    int totalItem();
    List<AccountDTO> getAllAccountsPagination(Pageable pageable);

    AccountDTO registerAccount(AccountDTO account);
    AccountDTO editAccount(AccountDTO account);
    void deleteAccount(Integer id);

}
