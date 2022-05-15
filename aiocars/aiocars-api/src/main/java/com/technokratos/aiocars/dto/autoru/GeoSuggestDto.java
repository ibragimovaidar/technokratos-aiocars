package com.technokratos.aiocars.dto.autoru;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class GeoSuggestDto {

    private String letters;

    @JsonProperty("geo_id")
    private String geoId;

    @JsonProperty("geo_radius")
    private String geoRadius;
}
