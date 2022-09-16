package com.example.ContactsManagement.Service;

import java.io.ByteArrayInputStream;

public interface ExcelService {
    ByteArrayInputStream loadListContact();
    ByteArrayInputStream loadListUser();
    ByteArrayInputStream loadTwoList();
}
