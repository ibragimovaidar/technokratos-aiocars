package com.technokratos.aiocars.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AiocarsServiceException{

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
