package com.example.ContactsManagement.Service;

import com.example.ContactsManagement.Entity.RefreshToken;

public interface RefreshTokenService {
     RefreshToken createRefreshToken(Integer idUser);
     RefreshToken verifyExpiration(RefreshToken token);
     int deleteByUserId(Integer userId);
}
