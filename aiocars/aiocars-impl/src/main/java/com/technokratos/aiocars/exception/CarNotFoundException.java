package com.technokratos.aiocars.exception;

public class CarNotFoundException extends NotFoundException {

    public CarNotFoundException(){
        super("Car not found");
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}
