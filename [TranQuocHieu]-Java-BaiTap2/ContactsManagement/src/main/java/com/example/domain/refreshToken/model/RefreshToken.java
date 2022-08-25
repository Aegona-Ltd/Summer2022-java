package com.example.domain.refreshToken.model;

import com.example.domain.users.model.User;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "refreshtokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, unique = true, name = "token")
    private String token;

    @Column(nullable = false, name = "expiry_date")
    private Instant expiryDate;
}
