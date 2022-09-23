package com.management.InventoryManagement.Controller;

import com.management.InventoryManagement.Config.JwtTokenProvider;
import com.management.InventoryManagement.DTO.UserAccountDTO;
import com.management.InventoryManagement.Entity.UserDetailsImpl;
import com.management.InventoryManagement.Payload.Response.ErrorResponse;
import com.management.InventoryManagement.Payload.Response.LoginResponse;
import com.management.InventoryManagement.Payload.Response.RegisterResponse;
import com.management.InventoryManagement.Service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/UserAccount")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserAccountDTO userAccountDTO) {
        if (userAccountService.isLogin(userAccountDTO)) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userAccountDTO.getUserName(),
                            userAccountDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails =(UserDetailsImpl) authentication.getPrincipal();
            String jwt = jwtTokenProvider.generateToken(userAccountDTO.getUserName());
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getUsername(), 200,
                    "Login successfully", true, roles));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse( HttpStatus.NOT_FOUND.value(),
                "Username or Password inValid", false));
    }

    @PostMapping("/registerAccount")
    public ResponseEntity<?> registerAccount(@RequestBody UserAccountDTO userAccountDTO){
        RegisterResponse response = new RegisterResponse();
        response.setMessage("Register successfully");
        response.setUsername(userAccountDTO.getUserName());
        response.setIsSuccess(true);
        response.setResultCode(200);
        userAccountService.registerAccount(userAccountDTO);
        return ResponseEntity.ok(response);
    }

}
