package com.example.ContactsManagement.Controller;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.DTO.Output.AccountOutput;
import com.example.ContactsManagement.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountRestController {
    @Autowired
    AccountService accountService;

    @GetMapping()
    public List<AccountDTO> getAllAccounts(){
        return accountService.getAllAccounts();
    }

//
    @GetMapping("/pageable")
    public AccountOutput getAllAccountsPageable(@RequestParam("page") int page,
                                                   @RequestParam("limit") int limit){
        AccountOutput output = new AccountOutput();
        output.setPage(page);
        output.setTotalPages((int) Math.ceil((double) (accountService.totalItem()/limit)));
        output.setListResults(accountService.getAllAccountsPagination(PageRequest.of(page-1, limit)));
        return output;
    }

    @PostMapping()
    public ResponseEntity registerAccount(@RequestBody @Valid AccountDTO accountDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(accountService.registerAccount(accountDTO));
    }

    @PutMapping()
    public ResponseEntity editAccount(@RequestBody @Valid AccountDTO accountDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(accountService.registerAccount(accountDTO));
    }

    @DeleteMapping("delete/{id}")
    public void deleteAccount(@PathVariable Integer id){
        accountService.deleteAccount(id);
    }
}
