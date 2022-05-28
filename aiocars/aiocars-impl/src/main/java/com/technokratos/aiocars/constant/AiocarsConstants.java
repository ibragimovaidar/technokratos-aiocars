package com.technokratos.aiocars.constant;

public interface AiocarsConstants {

    String ROLE = "ROLE";
    String PRIVILEGE = "PRIVILEGE";
    String AUTHORIZATION = "Authorization";
    String BEARER = "Bearer ";

    Integer POSTGIS_SRID = 4326;

    String RABBIT_EMAIL_NOTIFICATION_SERVICE_ROUTING_KEY = "notifications.email";
    String RABBIT_TELEGRAM_NOTIFICATION_SERVICE_ROUTING_KEY = "notifications.telegram";
}
