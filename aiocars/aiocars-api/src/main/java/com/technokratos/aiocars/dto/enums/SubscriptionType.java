package com.technokratos.aiocars.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubscriptionType {

    TELEGRAM("notifications.telegram"),
    EMAIL("notifications.email");

    private final String rabbitRoutingKey;
}
