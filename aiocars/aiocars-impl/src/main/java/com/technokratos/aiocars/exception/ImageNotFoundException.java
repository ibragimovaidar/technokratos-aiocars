package com.technokratos.aiocars.exception;

public class ImageNotFoundException extends NotFoundException {

    public ImageNotFoundException(){
        super("Image not found");
    }

    public ImageNotFoundException(String message) {
        super(message);
    }
}
