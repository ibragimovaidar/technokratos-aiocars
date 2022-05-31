package com.technokratos.aiocars.controller;

import com.technokratos.aiocars.api.UserApi;
import com.technokratos.aiocars.dto.TokenCoupleResponse;
import com.technokratos.aiocars.dto.request.UserRegisterRequest;
import com.technokratos.aiocars.dto.request.UserRequest;
import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public UUID createUser(UserRegisterRequest user) {
        return userService.register(user);
    }

    @Override
    public TokenCoupleResponse login(UserRequest userRequest) {
        return userService.login(userRequest);
    }

    @Override
    public UserResponse getUser(UserDetails userDetails) {
        return userService.getByUsername(userDetails.getUsername());
    }
}
