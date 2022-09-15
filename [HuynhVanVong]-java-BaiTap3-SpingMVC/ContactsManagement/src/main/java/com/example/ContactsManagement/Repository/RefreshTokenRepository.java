package com.example.ContactsManagement.Repository;

import com.example.ContactsManagement.Entity.Account;
import com.example.ContactsManagement.Entity.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

//    @Modifying
//    int deleteByUser(Account account);
}
