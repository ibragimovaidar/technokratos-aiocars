package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.response.ImageMetadataResponse;
import com.technokratos.aiocars.model.ImageMetadataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ImageMetadataMapper {

    ImageMetadataResponse toResponse(ImageMetadataEntity imageMetadata);
}
