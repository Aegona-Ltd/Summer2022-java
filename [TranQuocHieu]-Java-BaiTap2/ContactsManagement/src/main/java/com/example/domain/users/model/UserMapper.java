package com.example.domain.users.model;

import com.example.config.SecurityAdapter;
import com.example.domain.users.model.dto.CreateUserDTO;
import com.example.domain.users.model.dto.UpdateUserDTO;
import com.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    public static UserPrincipal userToPrincipal(User user) {

        UserPrincipal principal = new UserPrincipal();
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());
        principal.setUsername(user.getEmail());
        principal.setPassword(user.getPassword());
        principal.setEnabled(true);
        principal.setAuthorities(authorities);
        return principal;
    }

    public static User CreateUserDTOToPrincipal(CreateUserDTO userDTO) {
        User user = new User();
        user.setId(0);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(SecurityAdapter.passwordEncoder().encode(userDTO.getPassword()));
        return user;
    }

    public static UpdateUserDTO UserToUpdateUserDTO(User user) {
        UpdateUserDTO userDTO = new UpdateUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
