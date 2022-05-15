package com.technokratos.aiocars.security.filter;

import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.exception.AuthenticationHeaderException;
import com.technokratos.aiocars.service.jwt.JwtTokenService;
import com.technokratos.aiocars.util.HttpRequestUtil;
import com.technokratos.aiocars.util.HttpResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.technokratos.aiocars.constant.AiocarsConstants.AUTHORIZATION;

@Slf4j
public class TokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    private final JwtTokenService jwtTokenService;

    public TokenAuthenticationFilter(JwtTokenService jwtTokenService, AuthenticationManager authenticationManager) {
        this.jwtTokenService = jwtTokenService;
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String token = getTokenFromAuthorizationHeader(((HttpServletRequest) request).getHeader(AUTHORIZATION));
            if (Objects.nonNull(token)){
                UserResponse userResponse = jwtTokenService.getUserInfoByToken(token);
                SecurityContextHolder.getContext().setAuthentication(new PreAuthenticatedAuthenticationToken(userResponse, token));
            }
            chain.doFilter(request, response);
        } catch (Exception exception){
            SecurityContextHolder.clearContext();
            HttpResponseUtil.putExceptionInResponse(((HttpServletRequest) request), ((HttpServletResponse) response),
                    exception, HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String getTokenFromAuthorizationHeader(String authorizationHeader){
        if (authorizationHeader == null){
            return null;
        }
        log.info("Loading user for Authorization header: {}", authorizationHeader);
        if (!authorizationHeader.startsWith("Bearer")){
            throw new AuthenticationHeaderException("Invalid authentication scheme found in Authorization header");
        }

        String token = HttpRequestUtil.getTokenFromAuthorizationHeader(authorizationHeader);
        if (token == null){
            throw new AuthenticationHeaderException("Authorization header token is empty");
        }
        return token;
    }
}
