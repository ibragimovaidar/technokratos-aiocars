package com.technokratos.aiocars.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequest {

    private String name;

    private String description;

    @JsonProperty(required = true)
    private LightBrandRequest brand;

    private List<LightImageRequest> images;
}
