package com.example.controller.login;

import com.example.config.JwtProvider;
import com.example.domain.restresult.RestResultError;
import com.example.domain.role.model.JwtResult;
import com.example.domain.users.service.UsersService;
import com.example.domain.users.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class LoginRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UsersService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDTO form, BindingResult bindingResult) {
        RestResultError result = service.loginAccount(form, bindingResult);
        if (result.getResult()==0){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtResult(0, result.getMessage(), jwt));
        }
        return ResponseEntity.ok().body(result);
    }
}
