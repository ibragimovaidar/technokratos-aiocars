package com.technokratos.aiocars.exception;

public class SubscriptionNotFoundException extends NotFoundException {

    public SubscriptionNotFoundException(){
        super("Subscription not found");
    }

    public SubscriptionNotFoundException(String message) {
        super(message);
    }
}
