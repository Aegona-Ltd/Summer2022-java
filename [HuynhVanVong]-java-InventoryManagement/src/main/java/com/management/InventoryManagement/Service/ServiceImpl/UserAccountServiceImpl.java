package com.management.InventoryManagement.Service.ServiceImpl;

import com.management.InventoryManagement.DTO.UserAccountDTO;
import com.management.InventoryManagement.Entity.EmailDetails;
import com.management.InventoryManagement.Entity.UserAccount;
import com.management.InventoryManagement.Entity.UserDetailsImpl;
import com.management.InventoryManagement.Reposistory.UserAccountReposistory;
import com.management.InventoryManagement.Service.EmailService;
import com.management.InventoryManagement.Service.UserAccountService;
import com.management.InventoryManagement.Utils.Convert;
import com.management.InventoryManagement.Utils.RandomPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    UserAccountReposistory userAccountReposistory;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    Convert convert;
    @Autowired
    private RandomPassword randomPassword;

    @Autowired
    private EmailService emailService;

    @Override
    public UserAccount findByUserName(String userName) {
        return userAccountReposistory.findByUserNameEquals(userName);
    }

    @Override
    public List<UserAccountDTO> findAllAccounts() {
        return null;
    }

    @Override
    public int totalItem() {
        return 0;
    }

    @Override
    public UserAccountDTO registerAccount(UserAccountDTO account) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUserName(account.getUserName());
        userAccountDTO.setEmail(account.getEmail());
        userAccountDTO.setFullName(account.getFullName());
        userAccountDTO.setPhoneNumber(account.getPhoneNumber());
        String password = randomPassword.alphaNumericString();
        userAccountDTO.setPassword(passwordEncoder.encode(password));
        UserAccount userAccount = convert.toEntity(userAccountDTO, UserAccount.class);
        UserAccountDTO userAccountDTOResponse = convert.toDto(userAccountReposistory.save(userAccount),
                UserAccountDTO.class);

        EmailDetails emailDetails = new EmailDetails(userAccount.getEmail(), "Your Password: "
                + password, "Password App");
        emailService.sendSimpleMail(emailDetails);

        return userAccountDTOResponse;
    }

    @Override
    public UserAccountDTO editAccount(UserAccountDTO account) {
        return null;
    }

    @Override
    public void deleteAccount(Integer id) {

    }

    @Override
    public Boolean isLogin(UserAccountDTO account) {
        UserAccount accountInDB = userAccountReposistory.findByUserNameEquals(account.getUserName());
        if (accountInDB == null) {
            return false;
        } else if (!passwordEncoder.matches(account.getPassword(), accountInDB.getPassword())) {
            return false;
        } else if (accountInDB.isDeleted()) {
            return false;
        }
        return true;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount account = userAccountReposistory.findByUserNameEquals(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(account);
    }
}
