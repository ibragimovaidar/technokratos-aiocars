package com.technokratos.aiocars.service.impl;

import com.technokratos.aiocars.dto.request.ImageMetadataRequest;
import com.technokratos.aiocars.dto.response.ImageMetadataResponse;
import com.technokratos.aiocars.exception.ImageNotFoundException;
import com.technokratos.aiocars.repository.ImageMetadataRepository;
import com.technokratos.aiocars.service.ImageService;
import com.technokratos.aiocars.service.cdn.ImageCdnService;
import com.technokratos.aiocars.util.mapper.ImageMetadataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageCdnService imageCdnService;

    private final ImageMetadataMapper imageMetadataMapper;

    private final ImageMetadataRepository imageMetadataRepository;

    @Override
    public ImageMetadataResponse getById(UUID id) {
        return imageMetadataMapper.toResponse(
                imageMetadataRepository.findById(id).orElseThrow(ImageNotFoundException::new));
    }

    @Override
    public ImageMetadataResponse upload(MultipartFile image) {
        return imageMetadataMapper.toResponse(imageCdnService.upload(image));
    }

    @Override
    public ImageMetadataResponse upload(MultipartFile image, int height, int width) {
        return imageMetadataMapper.toResponse(imageCdnService.uploadResizedImage(image, height, width));
    }

    @Override
    public ImageMetadataResponse upload(ImageMetadataRequest imageMetadataRequest) {
        byte[] image = Base64.getDecoder().decode(imageMetadataRequest.getImage());
        return imageMetadataMapper.toResponse(
                imageCdnService.upload(image, imageMetadataRequest.getHeight(), imageMetadataRequest.getWidth()));
    }
}
