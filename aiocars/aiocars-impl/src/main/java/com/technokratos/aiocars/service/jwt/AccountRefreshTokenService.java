package com.technokratos.aiocars.service.jwt;

import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.model.RefreshTokenEntity;

public interface AccountRefreshTokenService {

    RefreshTokenEntity generateRefreshToken(UserResponse accountResponse);

    RefreshTokenEntity verifyRefreshTokenExpiryDate(String refreshToken);
}
