package com.technokratos.aiocars.service.scheduling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.aiocars.dto.messaging.NotificationMessageDto;
import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import com.technokratos.aiocars.dto.response.CarResponse;
import com.technokratos.aiocars.model.SubscriptionEntity;
import com.technokratos.aiocars.repository.SubscriptionRepository;
import com.technokratos.aiocars.util.mapper.NotificationMessageMapper;
import com.technokratos.aiocars.util.mapper.UserMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionTask implements Runnable {

    private SubscriptionRepository subscriptionRepository;

    private ObjectMapper objectMapper;

    private NotificationMessageMapper notificationMessageMapper;

    private UserMapper userMapper;

    private RabbitTemplate rabbitTemplate;

    private TopicExchange topicExchange;

    private AdvertisementResponse advertisementResponse;

    private CarResponse carResponse;

    @Override
    public void run() {
        List<SubscriptionEntity> subscriptions = subscriptionRepository.findAllMatchingAdvertisement(
                advertisementResponse.getCar().getId(),
                advertisementResponse.getCity().getId(),
                advertisementResponse.getMileage(),
                advertisementResponse.getYear(),
                advertisementResponse.getPrice());
        subscriptions.forEach(
                subscription -> {
                    NotificationMessageDto notificationMessage = NotificationMessageDto.builder()
                            .target(subscription.getTarget())
                            .user(notificationMessageMapper.toUserDto(
                                    userMapper.toLightResponse(subscription.getUser())))
                            .car(notificationMessageMapper.toCarDto(carResponse))
                            .advertisements(List.of(notificationMessageMapper.toAdvertisementDto(advertisementResponse)))
                            .build();
                    String routingKey = subscription.getType().getRabbitRoutingKey();
                    try {
                        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey,
                                objectMapper.writeValueAsString(notificationMessage));
                        log.info("Notification message was sent to topic '{}', routingKey '{}'",
                                topicExchange.getName(), routingKey);
                    } catch (JsonProcessingException e) {
                        log.error("", e);
                    }
                }
        );
    }
}
