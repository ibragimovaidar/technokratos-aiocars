package com.technokratos.aiocars.service.jwt.impl;

import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.exception.TokenRefreshException;
import com.technokratos.aiocars.exception.UserNotFoundException;
import com.technokratos.aiocars.model.RefreshTokenEntity;
import com.technokratos.aiocars.model.UserRefreshTokenEntity;
import com.technokratos.aiocars.repository.UserRefreshTokenRepository;
import com.technokratos.aiocars.repository.UserRepository;
import com.technokratos.aiocars.service.jwt.UserRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class UserRefreshTokenServiceImpl implements UserRefreshTokenService {

    @Value("${jwt.expiration.refresh.millis}")
    private long expirationRefreshInMillis;

    private final UserRefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public RefreshTokenEntity generateRefreshToken(UserResponse accountResponse) {
        return refreshTokenRepository.save(
                UserRefreshTokenEntity.builder()
                        .expiryDate(Instant.now().plusMillis(expirationRefreshInMillis))
                        .userEntity(userRepository
                                .findOneByUsername(accountResponse.getUsername())
                                .orElseThrow(UserNotFoundException::new))
                        .build()
        );
    }

    @Override
    public RefreshTokenEntity verifyRefreshTokenExpiryDate(String refreshToken) {
        return refreshTokenRepository.findById(UUID.fromString(refreshToken))
                .map(token -> {
                    refreshTokenRepository.delete(token);
                    if (token.getExpiryDate().isBefore(Instant.now())) {
                        throw new TokenRefreshException(token.getId().toString(), "Refresh token is expired");
                    }
                    return refreshTokenRepository.save(
                            UserRefreshTokenEntity.builder()
                                    .expiryDate(Instant.now().plusMillis(expirationRefreshInMillis))
                                    .userEntity(token.getUserEntity())
                                    .build());
                })
                .orElseThrow(() -> new TokenRefreshException(refreshToken, "Token does not exist"));
    }
}
