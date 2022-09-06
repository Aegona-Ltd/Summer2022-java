package com.example.domain.users.model.dto;

import com.example.domain.role.model.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateUserDTO {
    private Integer id;
    @NotNull(message = "Please enter name!!")
    private String name;
    private String email;
    @NotNull(message = "Please choose roles")
    private List<Role> roles;
}
