package com.technokratos.aiocars.provider;

import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.model.RefreshTokenEntity;

import java.util.List;

public interface JwtRefreshTokenProvider {

    String generateRefreshToken(UserResponse accountResponse);

    RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken);
}
