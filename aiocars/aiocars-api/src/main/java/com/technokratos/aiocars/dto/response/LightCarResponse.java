package com.technokratos.aiocars.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LightCarResponse {

    private UUID id;

    private String name;

    private String description;

    private LightBrandResponse brand;
}
