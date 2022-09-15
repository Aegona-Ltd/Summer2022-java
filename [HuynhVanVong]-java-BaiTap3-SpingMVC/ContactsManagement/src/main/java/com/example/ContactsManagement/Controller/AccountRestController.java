package com.example.ContactsManagement.Controller;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.DTO.Output.AccountOutput;
import com.example.ContactsManagement.DTO.Output.RestResultError;
import com.example.ContactsManagement.Service.AccountService;
import com.example.ContactsManagement.config.JwtTokenProvider;
import com.example.ContactsManagement.Payload.response.LoginResponse;
import com.example.ContactsManagement.Payload.response.logoutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountRestController {
    @Autowired
    AccountService accountService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AccountDTO accountDTO, BindingResult bindingResult) {
        // Xác thực từ username và password.
        RestResultError result = accountService.loginAccount(accountDTO, bindingResult);
        if (result.getResult() == 0) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountDTO.getUserName(),
                        accountDTO.getPassword()
                )
        );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(accountDTO.getUserName());
            LoginResponse response = new LoginResponse(jwt, accountDTO.getUserName(), true);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok().body(result);
    }
    @GetMapping()
    public List<AccountDTO> getAllAccounts(){
        return accountService.getAllAccounts();
    }
//    @PreAuthorize("isAuthenticated")
    @GetMapping("/pageable") @PreAuthorize("hasAnyRole('ADMIN')")
    public AccountOutput getAllAccountsPageable(@RequestParam("page") int page,
                                                   @RequestParam("limit") int limit){
        AccountOutput output = new AccountOutput();
        output.setPage(page);
        output.setTotalPages((int) Math.ceil((double) (accountService.totalItem()/limit)));
        output.setListResults(accountService.getAllAccountsPagination(PageRequest.of(page-1, limit)));
        return output;
    }

    @PostMapping()@PermitAll
    public ResponseEntity registerAccount(@RequestBody @Valid AccountDTO accountDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(accountService.registerAccount(accountDTO));
    }

    @PutMapping()@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity editAccount(@RequestBody @Valid AccountDTO accountDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(accountService.editAccount(accountDTO));
    }

    @DeleteMapping("delete/{id}") @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteAccount(@PathVariable Integer id){
        accountService.deleteAccount(id);
    }

    @GetMapping("logout")
    public logoutResponse logout(HttpServletRequest request, HttpServletResponse response){
       return accountService.logoutAccount(request, response);
    }

}
