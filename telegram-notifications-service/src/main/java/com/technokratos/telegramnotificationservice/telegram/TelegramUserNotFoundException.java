package com.technokratos.telegramnotificationservice.telegram;

public class TelegramUserNotFoundException extends RuntimeException {

    public TelegramUserNotFoundException(){
        super("Telegram user not found");
    }

    public TelegramUserNotFoundException(String message) {
        super(message);
    }
}
