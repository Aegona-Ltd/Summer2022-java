package com.management.InventoryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserAccount")
public class UserAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    @Column(name = "username", nullable = false)
    private String userName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "fullName", nullable = false)
    private String fullName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @JsonIgnore
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
    private List<Authority> authorities;
    @JsonIgnore
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
    private List<LogProductTransaction> logProductTransactions;
}
