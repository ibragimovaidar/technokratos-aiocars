package com.technokratos.aiocars.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AiocarsServiceException extends RuntimeException {

    private final HttpStatus httpStatus;

    public AiocarsServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
