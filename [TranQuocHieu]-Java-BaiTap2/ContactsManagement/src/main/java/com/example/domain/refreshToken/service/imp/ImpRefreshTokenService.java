package com.example.domain.refreshToken.service.imp;

import com.example.domain.refreshToken.model.RefreshToken;
import com.example.domain.refreshToken.service.RefreshTokenService;
import com.example.exception.TokenRefreshException;
import com.example.repository.RefreshTokenRepository;
import com.example.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImpRefreshTokenService implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Value("${jwt.refreshExpirationDateInMs}")
    private Long refreshExpirationDateInMs;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Integer userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(usersRepository.findById(userId).orElse(null));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshExpirationDateInMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    @Override
    public void deleteTokenById(Long id) {
        refreshTokenRepository.deleteById(id);
    }

}
