package com.example.ContactsManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Integer id;
    private LocalDateTime date;
    private String fullName;
    private String email;
    private String phone;
    private String message;
}
