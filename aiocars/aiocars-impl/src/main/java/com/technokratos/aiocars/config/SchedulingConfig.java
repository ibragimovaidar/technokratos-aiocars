package com.technokratos.aiocars.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SchedulingConfig {

    @Bean
    public ThreadPoolTaskExecutor subscriptionTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setThreadNamePrefix("SubscriptionSingleThreadTaskExecutor");
        taskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        return taskExecutor;
    }
}
