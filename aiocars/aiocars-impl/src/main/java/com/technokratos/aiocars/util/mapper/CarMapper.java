package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.request.CarRequest;
import com.technokratos.aiocars.dto.response.CarResponse;
import com.technokratos.aiocars.model.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", uses = {BrandMapper.class, ImageMetadataMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CarMapper {

    CarResponse toResponse(CarEntity car);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "advertisements", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "parserData", ignore = true)
    CarEntity toEntity(CarRequest car);
}
