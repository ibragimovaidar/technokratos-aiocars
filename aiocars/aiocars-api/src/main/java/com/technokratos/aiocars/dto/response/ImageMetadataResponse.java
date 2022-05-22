package com.technokratos.aiocars.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageMetadataResponse {

    private UUID id;

    private String url;

    private String height;

    private String width;

    private String name;

    private String description;
}
