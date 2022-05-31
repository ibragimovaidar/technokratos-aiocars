package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import com.technokratos.aiocars.model.AdvertisementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        uses = {ImageMetadataMapper.class,
                CityMapper.class,
                LocationMapper.class,
                UserMapper.class
        },
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AdvertisementMapper {

    @Mapping(source = "user", target = "seller")
    AdvertisementResponse toResponse(AdvertisementEntity advertisement);
}
