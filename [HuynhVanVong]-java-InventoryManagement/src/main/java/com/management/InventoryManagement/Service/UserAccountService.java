package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.DTO.UserAccountDTO;
import com.management.InventoryManagement.Entity.UserAccount;
import com.management.InventoryManagement.Payload.Request.ChangePasswordRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserAccountService extends UserDetailsService {
    UserAccount findByUserName(String userName);
    UserAccountDTO findByUserId(Integer userId);
    List<UserAccountDTO> findUsersByIsDeleted();
    List<UserAccountDTO> findUsersByIsNotDeleted();
    List<UserAccountDTO> findAllAccounts();
    int totalItem();
    UserAccountDTO registerAccount(UserAccountDTO account);
    UserAccountDTO updateAccount(UserAccountDTO account);
    Boolean updatePassword(String username, String oldPassword, String newPassword);
    void deleteAccount(Integer id);
    Boolean isLogin(UserAccountDTO account);

}
