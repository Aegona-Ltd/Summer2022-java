package com.example.ContactsManagement.ServiceImpl;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.DTO.Output.AccountOutput;
import com.example.ContactsManagement.Entity.Account;
import com.example.ContactsManagement.Repository.AccountReposistory;
import com.example.ContactsManagement.Service.AccountService;
import com.example.ContactsManagement.utils.Convert;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountReposistory AcccountReposistory;

    @Autowired
    Convert convert;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Account findByUserName(String username) {
        return AcccountReposistory.findByUserNameEquals(username);
    }

    @Override
    public Account findById(Integer id) {
        Optional<Account> account = AcccountReposistory.findById(id);
        return modelMapper.map(account.get(), Account.class);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> listAccounts = AcccountReposistory.findAll();
        List<AccountDTO> listAccountsDTO = listAccounts.stream().map(account -> convert.toDto(account, AccountDTO.class))
                .collect(Collectors.toList());
        return listAccountsDTO;
    }

    @Override
    public int totalItem() {
        return (int) AcccountReposistory.count();
    }

    @Override
    public List<AccountDTO> getAllAccountsPagination(Pageable pageable) {
        List<AccountDTO> listAccountsDTO = new ArrayList<>();
        List<Account> listAccounts = AcccountReposistory.findAll(pageable).getContent();
        for (Account account : listAccounts) {
            AccountDTO accountDTO = convert.toDto(account, AccountDTO.class);
            listAccountsDTO.add(accountDTO);
        }
        return listAccountsDTO;
    }

    @Override
    public AccountDTO registerAccount(AccountDTO accountDTO) {
        Account newAccount = convert.toEntity(accountDTO, Account.class);
        AccountDTO newAccountDTO = convert.toDto(AcccountReposistory.save(newAccount), AccountDTO.class);
        return newAccountDTO;
    }

    @Override
    public AccountDTO editAccount(AccountDTO accountDTO) {
        Account newAccount = new Account();
        if(accountDTO.getIdUser() != null) {
            Optional<Account> oldAccount = AcccountReposistory.findById(accountDTO.getIdUser());
            newAccount = modelMapper.map(oldAccount, Account.class);
        }
        newAccount = AcccountReposistory.save(newAccount);
        return convert.toDto(newAccount, AccountDTO.class);
    }

    @Override
    public void deleteAccount(Integer id) {
        AcccountReposistory.deleteById(id);
    }
}
