package com.technokratos.aiocars.service.cdn;

import com.technokratos.aiocars.model.ImageMetadataEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImageCdnService {

    String getUrlByUUID(UUID uuid);

    ImageMetadataEntity upload(MultipartFile image);

    ImageMetadataEntity upload(byte[] image, int height, int width);

    ImageMetadataEntity uploadResizedImage(MultipartFile image, int height, int width);
}
