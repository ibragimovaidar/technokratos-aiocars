package com.technokratos.aiocars.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandRequest {

    private String name;

    private String description;

    private List<LightImageRequest> images;

    private List<LightCarRequest> cars;
}
