package com.technokratos.aiocars.service;

import com.technokratos.aiocars.dto.response.UserResponse;

import java.util.Optional;

public interface AccountService {

    Optional<UserResponse> findBySubject(String subject);
}
