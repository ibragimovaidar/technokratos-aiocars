package com.technokratos.aiocars.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionFilterResponse {

    private Integer radius;

    private Integer minMileage;

    private Integer maxMileage;

    private Integer minYear;

    private Integer maxYear;

    private Long minPrice;

    private Long maxPrice;
}
