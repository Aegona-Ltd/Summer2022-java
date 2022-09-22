package com.management.InventoryManagement.Controller;

import com.management.InventoryManagement.Config.JwtTokenProvider;
import com.management.InventoryManagement.DTO.UserAccountDTO;
import com.management.InventoryManagement.Entity.UserDetailsImpl;
import com.management.InventoryManagement.Payload.Response.LoginResponse;
import com.management.InventoryManagement.Service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;


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
        return ResponseEntity.ok(new LoginResponse("null", "null", 403,
                "Username or Password inValid", false, null));
    }

}
