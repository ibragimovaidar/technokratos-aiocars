package com.technokratos.aiocars.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionFilterRequest {

    private Integer radius;

    private Integer minMileage;

    private Integer maxMileage;

    private Integer minYear;

    private Integer maxYear;

    private Long minPrice;

    private Long maxPrice;

}
