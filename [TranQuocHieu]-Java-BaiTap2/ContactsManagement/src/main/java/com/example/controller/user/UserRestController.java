package com.example.controller.user;

import com.example.domain.users.model.dto.CreateUserDTO;
import com.example.domain.users.model.dto.UpdateUserDTO;
import com.example.domain.users.service.UsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UsersService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("")
    public ResponseEntity<?> getUserList(@RequestParam(value = "page",defaultValue = "1",required = false) Integer page,
                                         @RequestParam (value = "size",defaultValue = "5",required = false) Integer size) {
        return ResponseEntity.ok().body(service.userList(page-1, size));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(service.deleteUser(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable(name = "id") Integer id) throws JsonProcessingException {
        return ResponseEntity.ok().body(service.getUser(id));
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateUserDTO userDTO,
                                        BindingResult bindingResult) {
        return ResponseEntity.ok().body(service.updateUser(userDTO, bindingResult));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        return ResponseEntity.ok().body(service.getUser(authentication.getName()));
    }

    @PutMapping("/upPass")
    public ResponseEntity<?> upPass(@RequestBody @Valid CreateUserDTO userDTO, BindingResult bindingResult) {
        return ResponseEntity.ok().body(service.upPass(userDTO, bindingResult));
    }

}
