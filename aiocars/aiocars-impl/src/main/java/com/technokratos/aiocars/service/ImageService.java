package com.technokratos.aiocars.service;

import com.technokratos.aiocars.dto.request.ImageMetadataRequest;
import com.technokratos.aiocars.dto.response.ImageMetadataResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageMetadataResponse upload(MultipartFile image);

    ImageMetadataResponse upload(MultipartFile image, int height, int width);

    ImageMetadataResponse upload(ImageMetadataRequest imageMetadataRequest);
}
