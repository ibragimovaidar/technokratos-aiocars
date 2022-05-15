package com.technokratos.aiocars.exception;

public class TokenRefreshException extends RuntimeException {

    private String refreshToken;

    public TokenRefreshException() {
        super();
    }

    public TokenRefreshException(String refreshToken, String message) {
        super(message);
    }
}
