package com.example.controller.contact;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/contactus")
public class ContactFormController {

    @GetMapping("")
    public String viewContact() {
        return "contactForm";
    }
}
