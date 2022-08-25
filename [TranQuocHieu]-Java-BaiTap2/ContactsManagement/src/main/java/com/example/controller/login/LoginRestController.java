package com.example.controller.login;

import com.example.config.JwtProvider;
import com.example.domain.refreshToken.model.RefreshToken;
import com.example.domain.refreshToken.service.RefreshTokenService;
import com.example.domain.request.TokenRefreshRequest;
import com.example.domain.response.TokenRefreshResponse;
import com.example.domain.restresult.RestResultError;
import com.example.domain.response.JwtResponse;
import com.example.domain.users.service.UsersService;
import com.example.domain.users.model.dto.UserDTO;
import com.example.domain.users.service.imp.ImpUsersService;
import com.example.exception.TokenRefreshException;
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

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDTO form, BindingResult bindingResult) {
        RestResultError result = service.loginAccount(form, bindingResult);
        if (result.getResult()==0){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(service.getUser(form.getEmail()).getData().getId());
            return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), result.getMessage()));
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtProvider.generateTokenFromUsername(user.getEmail());
                    ImpUsersService.setEmailAccount(user.getEmail());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
