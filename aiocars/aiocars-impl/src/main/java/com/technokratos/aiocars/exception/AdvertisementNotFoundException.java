package com.technokratos.aiocars.exception;

public class AdvertisementNotFoundException extends NotFoundException {

    public AdvertisementNotFoundException() {
        super("Advertisement not found");
    }

    public AdvertisementNotFoundException(String message) {
        super(message);
    }
}
