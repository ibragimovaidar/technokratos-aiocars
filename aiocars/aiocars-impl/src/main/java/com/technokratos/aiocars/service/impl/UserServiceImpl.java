package com.technokratos.aiocars.service.impl;

import com.technokratos.aiocars.dto.TokenCoupleResponse;
import com.technokratos.aiocars.dto.enums.Privilege;
import com.technokratos.aiocars.dto.enums.Role;
import com.technokratos.aiocars.dto.request.UserRegisterRequest;
import com.technokratos.aiocars.dto.request.UserRequest;
import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.exception.UserNotFoundException;
import com.technokratos.aiocars.model.PrivilegeEntity;
import com.technokratos.aiocars.model.RoleEntity;
import com.technokratos.aiocars.model.UserEntity;
import com.technokratos.aiocars.repository.PrivilegeRepository;
import com.technokratos.aiocars.repository.UserRepository;
import com.technokratos.aiocars.service.UserService;
import com.technokratos.aiocars.service.jwt.JwtTokenService;
import com.technokratos.aiocars.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PrivilegeRepository privilegeRepository;

    private final UserMapper userMapper;

    private final JwtTokenService jwtTokenService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UUID register(UserRegisterRequest userRegisterRequest) {
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        UserEntity user = userMapper.toEntity(userRegisterRequest);
        user.setRoles(Collections.singleton(getRole()));
        return userRepository.save(user).getId();
    }

    @Override
    public TokenCoupleResponse login(UserRequest userRequest) {
        return userRepository.findOneByUsername(userRequest.getUsername())
                .filter(user -> passwordEncoder.matches(userRequest.getPassword(), user.getHashPassword()))
                .map(user -> jwtTokenService.generateTokenCouple(userMapper.toResponse(user)))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserResponse getByUsername(String username) {
        return userRepository.findOneByUsername(username)
                .map(userMapper::toResponse)
                .orElseThrow(UserNotFoundException::new);
    }

    private RoleEntity getRole() {
        Set<PrivilegeEntity> privileges = privilegeRepository
                .findByPrivilegeIsIn(Set.of(Privilege.CREATE, Privilege.DELETE, Privilege.WRITE, Privilege.READ));
        return RoleEntity.builder()
                .role(Role.USER)
                .privileges(privileges)
                .build();
    }
}
