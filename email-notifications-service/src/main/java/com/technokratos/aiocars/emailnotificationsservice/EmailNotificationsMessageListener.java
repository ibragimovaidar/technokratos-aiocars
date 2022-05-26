package com.technokratos.aiocars.emailnotificationsservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.aiocars.emailnotificationsservice.messaging.EmailNotificationMessageDto;
import com.technokratos.aiocars.emailnotificationsservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EmailNotificationsMessageListener {

    private final EmailService emailService;

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "#{emailNotificationsQueue.name}")
    public void handle(String message){
        log.info("Message received");
        try {
            EmailNotificationMessageDto emailNotificationMessage =
                    objectMapper.readValue(message, EmailNotificationMessageDto.class);
            emailService.sendNotification(emailNotificationMessage);
        } catch (JsonProcessingException e) {
            log.error("Jackson message deserialization error", e);
        }
    }
}
