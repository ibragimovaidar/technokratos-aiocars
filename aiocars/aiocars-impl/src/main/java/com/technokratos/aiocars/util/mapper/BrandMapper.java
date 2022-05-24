package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.request.BrandRequest;
import com.technokratos.aiocars.dto.response.BrandResponse;
import com.technokratos.aiocars.model.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", uses = ImageMetadataMapper.class, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BrandMapper {

    BrandResponse toResponse(BrandEntity brand);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "cars", ignore = true)
    BrandEntity toEntity(BrandRequest request);
}
