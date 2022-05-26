package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.response.CityResponse;
import com.technokratos.aiocars.model.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CityMapper {

    CityResponse toResponse(CityEntity city);
}
