package com.example.controller.register;

import com.example.domain.restresult.RestResultError;
import com.example.domain.users.model.dto.CreateUserDTO;
import com.example.domain.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class RegisterRestController {

    @Autowired
    private UsersService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody @Valid CreateUserDTO userDTO, BindingResult bindingResult) {
        RestResultError result = service.addUser(userDTO, bindingResult);
        return ResponseEntity.ok().body(result);
    }
}
