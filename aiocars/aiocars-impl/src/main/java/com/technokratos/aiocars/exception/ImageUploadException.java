package com.technokratos.aiocars.exception;

import org.springframework.http.HttpStatus;

public class ImageUploadException extends AiocarsServiceException {

    public ImageUploadException(){
        super("Image upload exception", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ImageUploadException(String message) {
        super(message, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
