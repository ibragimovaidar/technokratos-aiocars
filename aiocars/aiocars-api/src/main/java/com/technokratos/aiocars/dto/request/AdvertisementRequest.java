package com.technokratos.aiocars.dto.request;

import com.technokratos.aiocars.dto.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertisementRequest {

    private String name;

    private String description;

    private Integer mileage;

    private Integer year;

    private Long price;

    private UUID carId;

    private List<LightImageRequest> images;

    private LocationDto location;
}
