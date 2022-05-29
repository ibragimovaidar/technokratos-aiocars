package com.technokratos.telegramnotificationservice.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
