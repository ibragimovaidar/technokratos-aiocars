package com.technokratos.telegramnotificationservice.service;

import com.technokratos.telegramnotificationservice.messaging.NotificationMessageDto;

public interface TelegramNotificationService {

    void sendNotification(NotificationMessageDto notificationMessage);
}
