package com.technokratos.aiocars.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementResponse {

    private UUID id;

    private String name;

    private String description;

    private Integer mileage;

    private Integer year;

    private Long price;

    private LightCarResponse car;

    private List<ImageMetadataResponse> images;

    private CityResponse city;

    private LocationDto location;

    private LightUserResponse seller;
}
