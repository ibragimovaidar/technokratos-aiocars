package com.technokratos.aiocars.exception;

public class BrandNotFoundException extends NotFoundException {

    public BrandNotFoundException(){
        super("Brand not found");
    }

    public BrandNotFoundException(String message) {
        super(message);
    }
}
