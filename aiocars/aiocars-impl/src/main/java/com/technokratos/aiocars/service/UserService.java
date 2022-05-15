package com.technokratos.aiocars.service;

import com.technokratos.aiocars.dto.TokenCoupleResponse;
import com.technokratos.aiocars.dto.request.UserRegisterRequest;
import com.technokratos.aiocars.dto.request.UserRequest;

import java.util.UUID;

public interface UserService {

    UUID register(UserRegisterRequest userRegisterRequest);

    TokenCoupleResponse login(UserRequest userRequest);
}
