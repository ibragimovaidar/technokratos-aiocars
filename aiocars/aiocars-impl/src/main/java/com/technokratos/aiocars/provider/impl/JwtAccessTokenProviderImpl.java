package com.technokratos.aiocars.provider.impl;

import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.exception.AuthenticationHeaderException;
import com.technokratos.aiocars.provider.JwtAccessTokenProvider;
import com.technokratos.aiocars.service.AccountService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.technokratos.aiocars.constant.AiocarsConstants.ROLE;

@RequiredArgsConstructor
@Component
public class JwtAccessTokenProviderImpl implements JwtAccessTokenProvider {

    private final AccountService accountService;

    @Value("${jwt.expiration.access.millis}")
    private long expirationAccessMillis;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public String generateAccessToken(String subject, Map<String, Object> data) {
        Map<String, Object> claims = new HashMap<>(data);
        claims.put(Claims.SUBJECT, subject);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(expirationAccessMillis)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public boolean validateAccessToken(String accessToken, String subject) {
        try {
            Claims claims = parseAccessToken(accessToken);
            String subjectFromToken = claims.getSubject();
            Date date = claims.getExpiration();
            return subject.equals(subjectFromToken) && date.after(new Date());
        } catch (ExpiredJwtException e) {
            throw new AuthenticationHeaderException("Token expired date error");
        }
    }

    @Override
    public UserResponse userInfoByToken(String token) {
        String subject = getSubjectFromAccessToken(token);
        return accountService.findBySubject(subject)
                .orElseThrow(() -> new AuthenticationHeaderException("User with given name was not found"));
    }

    @Override
    public Claims parseAccessToken(String accessToken) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(accessToken).getBody();
    }

    @Override
    public List<String> getRolesFromAccessToken(String accessToken) {
        try {
            return (List<String>) Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(accessToken).getBody().get(ROLE);
        } catch (ExpiredJwtException e) {
            return (List<String>) e.getClaims().get(ROLE);
        }

    }

    @Override
    public Date getExpirationDateFromAccessToken(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(accessToken)
                    .getBody().getExpiration();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getExpiration();
        }

    }

    @Override
    public String getSubjectFromAccessToken(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(accessToken).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }
}
