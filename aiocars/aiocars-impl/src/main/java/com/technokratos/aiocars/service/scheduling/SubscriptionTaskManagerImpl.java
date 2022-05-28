package com.technokratos.aiocars.service.scheduling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import com.technokratos.aiocars.dto.response.CarResponse;
import com.technokratos.aiocars.repository.SubscriptionRepository;
import com.technokratos.aiocars.util.mapper.NotificationMessageMapper;
import com.technokratos.aiocars.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionTaskManagerImpl implements SubscriptionTaskManager {

    private final SubscriptionRepository subscriptionRepository;

    private final RabbitTemplate rabbitTemplate;

    private final TopicExchange topicExchange;

    private final UserMapper userMapper;

    private final ObjectMapper objectMapper;

    private final ThreadPoolTaskExecutor subscriptionTaskExecutor;

    private final NotificationMessageMapper notificationMessageMapper;

    private SubscriptionTask build(AdvertisementResponse advertisementResponse, CarResponse carResponse) {
        return SubscriptionTask.builder()
                .subscriptionRepository(subscriptionRepository)
                .rabbitTemplate(rabbitTemplate)
                .topicExchange(topicExchange)
                .objectMapper(objectMapper)
                .notificationMessageMapper(notificationMessageMapper)
                .userMapper(userMapper)
                .advertisementResponse(advertisementResponse)
                .carResponse(carResponse)
                .build();
    }


    @Override
    public void handleSubscriptions(AdvertisementResponse advertisementResponse, CarResponse carResponse) {
        subscriptionTaskExecutor.execute(build(advertisementResponse, carResponse));
        log.info("Created new subscription task");
    }
}
