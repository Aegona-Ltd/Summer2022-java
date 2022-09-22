package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.DTO.UserAccountDTO;
import com.management.InventoryManagement.Entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserAccountService extends UserDetailsService {
    UserAccount findByUserName(String userName);
    List<UserAccountDTO> findAllAccounts();
    int totalItem();
    UserAccountDTO registerAccount(UserAccountDTO account);
    UserAccountDTO editAccount(UserAccountDTO account);
    void deleteAccount(Integer id);
    Boolean isLogin(UserAccountDTO account);
    void logout(HttpServletRequest request, HttpServletResponse response);

}
