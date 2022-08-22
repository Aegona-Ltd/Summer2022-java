package com.example.domain.users.model.result;

import com.example.domain.restresult.RestResult;
import com.example.domain.role.model.Role;
import com.example.domain.users.model.User;
import com.example.domain.users.model.dto.UpdateUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultUser {
    private RestResult restResult;
    private UpdateUserDTO data;
    private List<Role> roles;
}
