package com.technokratos.aiocars.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class CarResponse {

    private UUID id;

    private String name;

    private String description;

    private BrandResponse brand;

    private List<ImageMetadataResponse> images;
}
