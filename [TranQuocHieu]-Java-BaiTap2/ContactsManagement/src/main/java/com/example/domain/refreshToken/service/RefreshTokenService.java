package com.example.domain.refreshToken.service;


import com.example.domain.refreshToken.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(Integer userId);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteTokenById(Long id);
}
