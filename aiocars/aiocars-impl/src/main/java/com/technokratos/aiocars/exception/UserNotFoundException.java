package com.technokratos.aiocars.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AiocarsServiceException {

    public UserNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
