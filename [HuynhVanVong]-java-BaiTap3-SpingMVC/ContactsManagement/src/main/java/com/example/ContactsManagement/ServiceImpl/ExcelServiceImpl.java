package com.example.ContactsManagement.ServiceImpl;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.DTO.ContactDTO;
import com.example.ContactsManagement.Service.AccountService;
import com.example.ContactsManagement.Service.ContactService;
import com.example.ContactsManagement.Service.ExcelService;

import com.example.ContactsManagement.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    ContactService contactService;
    @Autowired
    AccountService accountService;

    @Override
    public ByteArrayInputStream loadListContact() {
        List<ContactDTO> listContacts = contactService.getAllContacts();
        ByteArrayInputStream in = ExcelHelper.contactsToExcel(listContacts);
        return in;
    }

    @Override
    public ByteArrayInputStream loadListUser() {
        List<AccountDTO> listAccounts = accountService.getAllAccounts();
        ByteArrayInputStream in = ExcelHelper.accountsToExcel(listAccounts);
        return in;
    }

    @Override
    public ByteArrayInputStream loadTwoList() {
        List<AccountDTO> listAccounts = accountService.getAllAccounts();
        List<ContactDTO> listContacts = contactService.getAllContacts();
        ByteArrayInputStream in = ExcelHelper.accountsAndUserToExcel(listAccounts, listContacts);
        return in;
    }


}
