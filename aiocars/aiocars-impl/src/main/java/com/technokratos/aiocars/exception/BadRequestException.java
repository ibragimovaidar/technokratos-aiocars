package com.technokratos.aiocars.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AiocarsServiceException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
