package com.example.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/login", "/"})
public class LoginController {
	
	@GetMapping("")
	public String loginPage() {
		return "login";
	}
}
