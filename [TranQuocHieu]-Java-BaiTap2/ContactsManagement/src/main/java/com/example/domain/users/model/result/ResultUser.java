package com.example.domain.users.model.result;

import com.example.domain.restresult.RestResult;
import com.example.domain.role.model.Role;
import com.example.domain.users.model.User;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultUser {
    private Integer result;
    private String message;
    @JsonSerialize(using = CustomUserSerializer.class)
    private User data;
    private List<Role> roles;
}
