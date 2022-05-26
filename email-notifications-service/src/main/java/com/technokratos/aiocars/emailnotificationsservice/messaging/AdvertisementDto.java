package com.technokratos.aiocars.emailnotificationsservice.messaging;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertisementDto {

    private UUID id;

    private String url;

    private Integer mileage;

    private Integer year;

    private String imageUrl;

    private String city;

    private Long price;
}
