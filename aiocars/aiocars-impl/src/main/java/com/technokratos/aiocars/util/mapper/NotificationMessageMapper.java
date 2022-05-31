package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.messaging.AdvertisementDto;
import com.technokratos.aiocars.dto.messaging.CarDto;
import com.technokratos.aiocars.dto.messaging.UserDto;
import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import com.technokratos.aiocars.dto.response.CarResponse;
import com.technokratos.aiocars.dto.response.LightUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface NotificationMessageMapper {

    @Mapping(target = "brand", source = "carResponse.brand.name")
    CarDto toCarDto(CarResponse carResponse);

    UserDto toUserDto(LightUserResponse lightUserResponse);

    @Mapping(target = "city", source = "advertisementResponse.city.name")
    @Mapping(target = "imageUrl", expression = "java(getImageUrl(advertisementResponse))")
    AdvertisementDto toAdvertisementDto(AdvertisementResponse advertisementResponse);

    default String getImageUrl(AdvertisementResponse advertisementResponse){
        if (Objects.nonNull(advertisementResponse.getImages()) && !advertisementResponse.getImages().isEmpty()){
            return advertisementResponse.getImages().get(0).getUrl();
        }
        return null;
    }
}
