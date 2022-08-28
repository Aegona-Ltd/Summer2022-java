package com.example.controller.contact;

import com.example.domain.contacts.model.Contact;
import com.example.domain.contacts.service.ContactService;
import com.example.excel.ExcelContactGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactService service;

    @GetMapping("")
    public String viewContactList() {
        return "contacts";
    }

    @GetMapping("/export-to-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Contact_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Contact> contacts = service.contactList("datatime");
        ExcelContactGenarator generator = new ExcelContactGenarator(contacts);
        generator.generateExcelFile(response);
    }
}
