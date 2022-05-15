package com.technokratos.aiocars.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationHeaderException extends AiocarsServiceException {

    public AuthenticationHeaderException() {
        super("Authenticatiomn exception", HttpStatus.UNAUTHORIZED);
    }

    public AuthenticationHeaderException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
