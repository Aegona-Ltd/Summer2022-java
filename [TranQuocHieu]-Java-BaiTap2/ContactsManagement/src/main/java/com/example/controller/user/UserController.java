package com.example.controller.user;

import com.example.domain.users.model.User;
import com.example.domain.users.service.UsersService;
import com.example.excel.ExcelUserGenerator;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("")
    public String viewUsers() {
        return "users";
    }

    @GetMapping("/export-to-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=user_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<User> userList = usersService.userList();
        ExcelUserGenerator generator = new ExcelUserGenerator(userList);
        generator.generateExcelFile(response);
    }
}
