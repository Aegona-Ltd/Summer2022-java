package com.example.domain.users.service;


import com.example.domain.restresult.RestResult;
import com.example.domain.restresult.RestResultError;
import com.example.domain.restresult.ResultList;
import com.example.domain.users.model.dto.CreateUserDTO;
import com.example.domain.users.model.dto.UpdateUserDTO;
import com.example.domain.users.model.dto.UserDTO;
import com.example.domain.users.model.result.ResultUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.validation.BindingResult;

public interface UsersService {

    RestResultError loginAccount(UserDTO account, BindingResult bindingResult);

    RestResultError addUser(CreateUserDTO userDTO, BindingResult bindingResult);

    ResultList userList(int page, int size);

    RestResult deleteUser(Integer id);

    ResultUser getUser(Integer id) throws JsonProcessingException;

    ResultUser getUser(String email);

    RestResultError updateUser(UpdateUserDTO userDTO, BindingResult bindingResult);

    RestResultError upPass(CreateUserDTO userDTO, BindingResult bindingResult);
}
