package com.technokratos.aiocars.service.impl;

import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.repository.UserRepository;
import com.technokratos.aiocars.service.AccountService;
import com.technokratos.aiocars.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserAccountServiceImpl implements AccountService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Optional<UserResponse> findBySubject(String subject) {
        return userRepository.findOneByUsername(subject).map(userMapper::toResponse);
    }
}
