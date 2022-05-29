package com.technokratos.telegramnotificationservice.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationMessageDto {

    private String target;

    private UserDto user;

    private CarDto car;

    private List<AdvertisementDto> advertisements;
}
