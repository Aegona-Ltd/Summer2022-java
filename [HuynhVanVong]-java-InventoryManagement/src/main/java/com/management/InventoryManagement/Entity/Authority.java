package com.management.InventoryManagement.Entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Authorities")
public class Authority implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorityID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private UserAccount userAccount;
    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role role;
}
