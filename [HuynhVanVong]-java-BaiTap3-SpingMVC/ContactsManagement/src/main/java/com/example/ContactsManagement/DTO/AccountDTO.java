package com.example.ContactsManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer idUser;
    private String userName;
    private String fullName;
    private String email;
    private String password;

}
