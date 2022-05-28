package com.technokratos.aiocars.dto.request;

import com.technokratos.aiocars.dto.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionRequest {

    private String target;

    private SubscriptionType type;

    private SubscriptionFilterRequest filter;

    private UUID carId;

    private UUID cityId;
}
