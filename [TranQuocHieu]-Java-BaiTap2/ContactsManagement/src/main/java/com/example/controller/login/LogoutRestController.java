package com.example.controller.login;

import com.example.domain.response.JwtResponse;
import com.example.domain.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LogoutRestController {

    @Autowired
    private UsersService service;


    @GetMapping("/logoutF")
    public ResponseEntity<?> logout(Authentication authentication) {
        service.logout();
        return ResponseEntity.ok(new JwtResponse());
    }
}
