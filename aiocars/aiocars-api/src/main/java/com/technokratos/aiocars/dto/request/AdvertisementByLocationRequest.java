package com.technokratos.aiocars.dto.request;

import com.technokratos.aiocars.dto.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertisementByLocationRequest {

    private Integer radius;

    private LocationDto location;
}
