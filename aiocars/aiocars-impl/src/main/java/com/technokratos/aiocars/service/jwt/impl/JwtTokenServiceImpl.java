package com.technokratos.aiocars.service.jwt.impl;

import com.technokratos.aiocars.constant.AiocarsConstants;
import com.technokratos.aiocars.dto.TokenCoupleDto;
import com.technokratos.aiocars.dto.TokenCoupleResponse;
import com.technokratos.aiocars.dto.response.RoleResponse;
import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.model.RefreshTokenEntity;
import com.technokratos.aiocars.provider.JwtAccessTokenProvider;
import com.technokratos.aiocars.provider.JwtRefreshTokenProvider;
import com.technokratos.aiocars.repository.UserRefreshTokenRepository;
import com.technokratos.aiocars.service.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.technokratos.aiocars.constant.AiocarsConstants.BEARER;
import static com.technokratos.aiocars.constant.AiocarsConstants.ROLE;

@RequiredArgsConstructor
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;
    private final JwtRefreshTokenProvider jwtRefreshTokenProvider;

    @Override
    public UserResponse getUserInfoByToken(String token) {
        return jwtAccessTokenProvider.userInfoByToken(token);
    }

    @Override
    public TokenCoupleResponse generateTokenCouple(UserResponse accountResponse) {
        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                accountResponse.getUsername(),
                Collections.singletonMap(
                        AiocarsConstants.ROLE,
                        accountResponse.getRoles().stream()
                                .map(RoleResponse::getRole)
                                .collect(Collectors.toList()))
        );
        String refreshToken = jwtRefreshTokenProvider.generateRefreshToken(accountResponse);
        return TokenCoupleResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpirationDate(jwtAccessTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }

    @Override
    public TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleDto) {
        RefreshTokenEntity verifiedRefreshToken = jwtRefreshTokenProvider.verifyRefreshTokenExpiration(tokenCoupleDto.getRefreshToken());

        List<String> roles = jwtAccessTokenProvider.getRolesFromAccessToken(
                tokenCoupleDto.getAccessToken().replace(BEARER.concat(StringUtils.SPACE), StringUtils.EMPTY));
        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                jwtAccessTokenProvider.getSubjectFromAccessToken(
                        tokenCoupleDto.getAccessToken().replace(BEARER.concat(StringUtils.SPACE), StringUtils.EMPTY)),
                Collections.singletonMap(ROLE, roles)
        );
        return TokenCoupleResponse.builder()
                .refreshToken(String.valueOf(verifiedRefreshToken.getId()))
                .accessToken(BEARER.concat(StringUtils.SPACE).concat(accessToken))
                .accessTokenExpirationDate(jwtAccessTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }
}
