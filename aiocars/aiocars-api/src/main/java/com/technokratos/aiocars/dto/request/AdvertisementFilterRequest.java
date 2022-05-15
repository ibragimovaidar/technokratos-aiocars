package com.technokratos.aiocars.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertisementFilterRequest {

    private String city;

    private Integer cityRadius;

    private Integer minMileage;

    private Integer maxMileage;

    private Integer minYear;

    private Integer maxYear;

}
