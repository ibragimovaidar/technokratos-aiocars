package com.technokratos.telegramnotificationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.telegramnotificationservice.messaging.NotificationMessageDto;
import com.technokratos.telegramnotificationservice.service.TelegramNotificationService;
import com.technokratos.telegramnotificationservice.telegram.AiocarsTelegramBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramNotificationMessageListener {

    private final TelegramNotificationService telegramNotificationService;

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "#{telegramNotificationsQueue.name}")
    public void handle(String message){
        try {
            NotificationMessageDto notificationMessage = objectMapper.readValue(message, NotificationMessageDto.class);
            telegramNotificationService.sendNotification(notificationMessage);
        } catch (Exception e) {
            log.error("Telegram notification service exception", e);
        }
    }
}
