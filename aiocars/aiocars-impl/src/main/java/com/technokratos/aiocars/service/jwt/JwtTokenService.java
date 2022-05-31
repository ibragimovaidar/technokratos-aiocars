package com.technokratos.aiocars.service.jwt;

import com.technokratos.aiocars.dto.TokenCoupleDto;
import com.technokratos.aiocars.dto.TokenCoupleResponse;
import com.technokratos.aiocars.dto.response.UserResponse;

public interface JwtTokenService {

    UserResponse getUserInfoByToken(String token);

    TokenCoupleResponse generateTokenCouple(UserResponse accountResponse);

    TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleResponse);
    
}
