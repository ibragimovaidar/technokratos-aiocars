package com.technokratos.aiocars.dto.response;

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
public class SubscriptionResponse {

    private UUID id;

    private String target;

    private SubscriptionType type;

    private SubscriptionFilterResponse filter;

    private LightCarResponse car;

    private CityResponse city;
}
