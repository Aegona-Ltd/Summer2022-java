package com.example.domain.users.service;


import com.example.domain.restResult.RestResult;
import com.example.domain.users.model.UserDTO;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

public interface UsersService {

    RestResult loginAccount(UserDTO account, BindingResult bindingResult);

    RestResult getAccountName(HttpServletRequest request);
}
