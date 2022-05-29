package com.technokratos.telegramnotificationservice.service.impl;

import com.technokratos.telegramnotificationservice.messaging.NotificationMessageDto;
import com.technokratos.telegramnotificationservice.service.TelegramNotificationService;
import com.technokratos.telegramnotificationservice.telegram.AiocarsTelegramBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TelegramNotificationServiceImpl implements TelegramNotificationService {

    private final AiocarsTelegramBot aiocarsTelegramBot;

    @Override
    public void sendNotification(NotificationMessageDto notificationMessage) {
        aiocarsTelegramBot.sendNotification(notificationMessage);
    }
}
