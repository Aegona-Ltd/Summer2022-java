package com.example.domain.users.service.imp;

import com.example.domain.restresult.RestResultError;
import com.example.domain.restresult.ResultList;
import com.example.domain.restresult.ResultMapper;
import com.example.domain.role.model.Role;
import com.example.domain.role.model.RoleName;
import com.example.domain.users.model.*;
import com.example.domain.users.model.dto.CreateUserDTO;
import com.example.domain.users.model.dto.UpdateUserDTO;
import com.example.domain.users.model.dto.UserDTO;
import com.example.domain.users.model.result.CustomUserSerializer;
import com.example.domain.users.model.result.ResultUser;
import com.example.domain.users.service.UsersService;
import com.example.domain.restresult.RestResult;
import com.example.repository.RefreshTokenRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImpUsersService implements UsersService {

    private static String emailAccount;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UsersRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    /*
    * Method check account on database
    * Method return RestResult:
    *   result 0: login success
    *   result 10: Wrong Email
    *   result 20: Wrong Password
    *   result 90: Wrong input value
    * */
    @Override
    public RestResultError loginAccount(@Valid UserDTO account, BindingResult bindingResult) {
        RestResultError resultError = new RestResultError();
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error: bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, null);
                errors.put(error.getField(), message);
            }
            resultError.setResult(90);
            resultError.setMessage("Wrong input value");
            resultError.setError(errors);
            return resultError;
        }

//        Get account in database
        User user = repository.findByEmail(account.getEmail()).orElse(null);

//        Check user db with user client
        if (user==null) {
            resultError.setResult(10);
            resultError.setMessage("Wrong email!!");
        }else if (!passwordEncoder.matches(account.getPassword(), user.getPassword())) {
            resultError.setResult(20);
            resultError.setMessage("Wrong password!!");
        }
        else {
            resultError.setResult(0);
            resultError.setMessage(user.getName());
            this.emailAccount = user.getEmail();
        }
        return resultError;
    }


    @Override
    public RestResultError addUser(CreateUserDTO userDTO, BindingResult bindingResult) {
        RestResultError result = new RestResultError();
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error: bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, null);
                errors.put(error.getField(), message);
            }
            result.setResult(90);
            result.setMessage("Wrong input value");
            result.setError(errors);
            return result;
        }
        //        Get account in database
        User user = repository.findByEmail(userDTO.getEmail()).orElse(null);

        if (user!=null) {
            result.setResult(10);
            result.setMessage("Email already exists!!");
        }else if (!userDTO.getPassword().equals(userDTO.getComfirm())) {
            result.setResult(20);
            result.setMessage("Wrong Password Confirm!!");
        }else {
            result.setResult(0);
            result.setMessage("Success");

            List<Role> roles = roleRepository.findByName(RoleName.USER).stream().toList();
            User user1 = UserMapper.CreateUserDTOToPrincipal(userDTO);
            user1.setRoles(roles);
            repository.save(user1);
        }
        return result;
    }

    @Override
    public ResultList userList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = repository.findAll(pageable);
        ResultList resultList = ResultMapper.pageableToResultList(users);
        return resultList;
    }

    @Override
    public RestResult deleteUser(Integer id) {
        RestResult result = new RestResult();
        result.setResult(0);
        result.setMessage("Success");
        List<Long> refreshTokenList = refreshTokenRepository.findByUserId(id);
        for (Long tokenId:refreshTokenList) {
            refreshTokenRepository.deleteById(tokenId);
        }
        repository.deleteById(id);
        return result;
    }

    @Override
    public ResultUser getUser(Integer id) {
        User user = repository.findById(id).orElse(null);
        ResultUser userSerializer = new ResultUser();
        userSerializer.setResult(0);
        userSerializer.setMessage("Success");
        userSerializer.setData(user);
        userSerializer.setRoles(roleRepository.findAll());
        return userSerializer;
    }

    @Override
    public ResultUser getUser(String email) {
        User user = repository.findByEmail(email).orElse(null);
        ResultUser userSerializer = new ResultUser();
        userSerializer.setResult(0);
        userSerializer.setMessage("Success");
        userSerializer.setData(user);
        userSerializer.setRoles(roleRepository.findAll());
        return userSerializer;
    }

    @Override
    @JsonSerialize(using = CustomUserSerializer.class)
    public List<User> userList() {
        return repository.findAll();
    }

    @Override
    public RestResultError updateUser(UpdateUserDTO userDTO, BindingResult bindingResult) {
        RestResultError result = new RestResultError();
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error: bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, null);
                errors.put(error.getField(), message);
            }
            result.setResult(90);
            result.setMessage("Wrong input value");
            result.setError(errors);
            return result;
        }
        result.setResult(0);
        result.setMessage("Success");
        User user = repository.findById(userDTO.getId()).orElse(null);
        user.setName(userDTO.getName());
        user.setRoles(userDTO.getRoles());
        repository.save(user);
        return result;
    }

    @Override
    public RestResultError upPass(CreateUserDTO userDTO, BindingResult bindingResult) {
        RestResultError result = new RestResultError();
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error: bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, null);
                errors.put(error.getField(), message);
            }
            result.setResult(90);
            result.setMessage("Wrong input value");
            result.setError(errors);
            return result;
        }

        User user = repository.findByEmail(userDTO.getEmail()).orElse(null);

        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            result.setResult(10);
            result.setMessage("Wrong password!!");
        }else {
            result.setResult(0);
            result.setMessage("Success");
            User user1 = new User();
            user1.setId(user.getId());
            user1.setName(user.getName());
            user1.setRoles(user.getRoles());
            user1.setEmail(user.getEmail());
            user1.setPassword(passwordEncoder.encode(userDTO.getComfirm()));
            repository.save(user1);
        }

        return result;
    }

    @Override
    public void logout() {
        User user = repository.findByEmail(this.emailAccount).orElse(null);
        if (user==null) return;
        List<Long> refreshTokenList = refreshTokenRepository.findByUserId(user.getId());
        for (Long tokenId:refreshTokenList) {
            refreshTokenRepository.deleteById(tokenId);
        }
        this.emailAccount = null;
    }

    public static String getEmailAccount() {
        return emailAccount;
    }

    public static void setEmailAccount(String emailAccount) {
        ImpUsersService.emailAccount = emailAccount;
    }
}
