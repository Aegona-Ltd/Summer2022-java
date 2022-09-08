package com.example.ContactsManagement.Controller;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountRestController {
    @Autowired
    AccountService accountService;

    @GetMapping()
    public List<AccountDTO> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping()
    public AccountDTO registerAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.registerAccount(accountDTO);
    }

    @PutMapping()
    public AccountDTO editAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.registerAccount(accountDTO);
    }

    @DeleteMapping("delete/{id}")
    public void deleteAccount(@PathVariable Integer id){
        accountService.deleteAccount(id);
    }
}
