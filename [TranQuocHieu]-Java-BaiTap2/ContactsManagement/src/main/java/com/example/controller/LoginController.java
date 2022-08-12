package com.example.controller;

import com.example.domain.customers.service.CustomerService;
import com.example.form.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private CustomerService service;
	
	@GetMapping("")
	public String loginPage(@ModelAttribute AccountForm loginForm) {
		return "login";
	}

	@PostMapping("")
	public String loginAccount(AccountForm loginForm) {
		loginForm = service.loginAccount(loginForm);
		return (loginForm==null) ? "redirect:/contactus": loginPage(loginForm);
	}
}
