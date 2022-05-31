package com.technokratos.aiocars.emailnotificationsservice.service;

import com.technokratos.aiocars.emailnotificationsservice.messaging.NotificationMessageDto;

public interface EmailService {

    void sendNotification(NotificationMessageDto emailNotificationMessage);
}
