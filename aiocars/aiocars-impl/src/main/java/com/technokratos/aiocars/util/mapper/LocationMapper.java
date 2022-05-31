package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.LocationDto;
import com.technokratos.aiocars.model.AdvertisementLocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface LocationMapper {

    LocationDto toResponse(AdvertisementLocationEntity advertisementLocation);
}
