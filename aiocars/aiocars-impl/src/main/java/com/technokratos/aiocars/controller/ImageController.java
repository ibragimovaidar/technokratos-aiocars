package com.technokratos.aiocars.controller;

import com.technokratos.aiocars.api.ImageApi;
import com.technokratos.aiocars.dto.request.ImageMetadataRequest;
import com.technokratos.aiocars.dto.response.ImageMetadataResponse;
import com.technokratos.aiocars.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ImageController implements ImageApi {

    private final ImageService imageService;

    @Override
    public ImageMetadataResponse upload(ImageMetadataRequest imageMetadataRequest) {
        return imageService.upload(imageMetadataRequest);
    }

    @Override
    public ImageMetadataResponse uploadMultipartFile(MultipartFile image, Integer width, Integer height) {
        if (Objects.nonNull(width) && Objects.nonNull(height)){
            return imageService.upload(image, height, width);
        }
        return imageService.upload(image);
    }

    @Override
    public ImageMetadataResponse getById(UUID id) {
        return imageService.getById(id);
    }
}
