package com.technokratos.aiocars.emailnotificationsservice.service;

import com.technokratos.aiocars.emailnotificationsservice.messaging.EmailNotificationMessageDto;

public interface EmailService {

    void sendNotification(EmailNotificationMessageDto emailNotificationMessage);
}
