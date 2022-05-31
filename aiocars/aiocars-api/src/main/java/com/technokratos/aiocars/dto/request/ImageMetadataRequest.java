package com.technokratos.aiocars.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageMetadataRequest {

    private String name;

    private String description;

    private Integer height;

    private Integer width;

    /** image as base64 string**/
    private String image;
}
