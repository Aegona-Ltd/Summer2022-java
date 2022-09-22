package com.management.InventoryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {
    private Integer userID;
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private boolean isDeleted;
}
