package com.technokratos.aiocars.api;

import com.technokratos.aiocars.dto.TokenCoupleResponse;
import com.technokratos.aiocars.dto.request.UserRegisterRequest;
import com.technokratos.aiocars.dto.request.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/users")
public interface UserApi {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createUser(@RequestBody UserRegisterRequest user);

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse login(@RequestBody UserRequest userRequest);
}

