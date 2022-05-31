package com.technokratos.aiocars.emailnotificationsservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableRabbit
@SpringBootApplication
public class EmailNotificationsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailNotificationsServiceApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

}
