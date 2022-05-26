package com.technokratos.aiocars.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityRequest {

    private String name;

    private String description;

    private Double lat;

    private Double lon;
}
