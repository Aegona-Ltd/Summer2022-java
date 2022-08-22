package com.example.controller.login;

import com.example.domain.role.model.JwtResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LogoutRestController {

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(new JwtResult());
    }
}
