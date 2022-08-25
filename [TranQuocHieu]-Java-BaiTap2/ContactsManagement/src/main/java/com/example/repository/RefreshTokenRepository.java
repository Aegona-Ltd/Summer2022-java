package com.example.repository;

import com.example.domain.refreshToken.model.RefreshToken;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Query(value = "SELECT id FROM REFRESHTOKENS WHERE USER_ID = ?1", nativeQuery = true)
    List<Long> findByUserId(Integer userId);
}
