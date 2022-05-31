package com.technokratos.aiocars.provider.impl;

import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.model.RefreshTokenEntity;
import com.technokratos.aiocars.provider.JwtRefreshTokenProvider;
import com.technokratos.aiocars.service.jwt.AccountRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtRefreshTokenProviderImpl implements JwtRefreshTokenProvider {

    private final AccountRefreshTokenService accountRefreshTokenService;

    @Override
    public String generateRefreshToken(UserResponse accountResponse) {
        return String.valueOf(accountRefreshTokenService.generateRefreshToken(accountResponse).getId());
    }

    @Override
    public RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken) {
        return accountRefreshTokenService.verifyRefreshTokenExpiryDate(refreshToken);
    }
}
