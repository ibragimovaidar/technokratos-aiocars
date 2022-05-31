package com.technokratos.aiocars.exception;

public class CityNotFoundException extends NotFoundException {

    public CityNotFoundException(){
        super("City not found");
    }

    public CityNotFoundException(String message) {
        super(message);
    }
}
