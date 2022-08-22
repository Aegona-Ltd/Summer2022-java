package com.example.controller.infoUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoUserController {
    @GetMapping("")
    public String viewInfo(){return "infoUser";}
}
