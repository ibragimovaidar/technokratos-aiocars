package com.technokratos.telegramnotificationservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${app.rabbitmq.host-name}")
    private String rabbitHostName;

    @Bean
    public ConnectionFactory connectionFactory(){
        return new CachingConnectionFactory(rabbitHostName);
    }

    @Bean
    public AmqpAdmin amqpAdmin(){
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("topic.exchange");
    }

    @Bean
    public Binding binding(TopicExchange topic,
                             Queue queue) {
        return BindingBuilder.bind(queue)
                .to(topic)
                .with("notifications.telegram");
    }

    @Bean
    public Queue telegramNotificationsQueue(){
        return new Queue("telegramNotificationsQueue");
    }
}
