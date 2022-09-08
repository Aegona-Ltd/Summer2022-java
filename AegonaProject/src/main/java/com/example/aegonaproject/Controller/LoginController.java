package com.example.AegonaProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class LoginController {



    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String getLoginPage() {
        return ("login");
    }


    @GetMapping("/view")
    public String getViewPage() {
        return ("view");
    }

    @GetMapping("/contacts")
    public String getContactPage() {
        return ("ContactUs");
    }

    @GetMapping("/layoutForm")
    public String getLayout() {
        return ("layout");
    }

}
