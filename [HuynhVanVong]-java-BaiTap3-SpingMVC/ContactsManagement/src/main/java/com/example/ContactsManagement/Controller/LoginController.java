package com.example.ContactsManagement.Controller;

import com.example.ContactsManagement.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class LoginController {
    @Autowired
    AccountService userService;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String getLoginPage() {
        return ("login");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/view")
    public String getViewPage() {
        return ("view");
    }

    @GetMapping("/contacts")
    public String getContactPage() {
        return ("ContactUs");
    }

}
