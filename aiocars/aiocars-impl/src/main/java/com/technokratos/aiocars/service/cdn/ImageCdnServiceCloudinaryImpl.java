package com.technokratos.aiocars.service.cdn;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.technokratos.aiocars.exception.AiocarsServiceException;
import com.technokratos.aiocars.exception.ImageNotFoundException;
import com.technokratos.aiocars.exception.ImageUploadException;
import com.technokratos.aiocars.model.ImageMetadataEntity;
import com.technokratos.aiocars.repository.ImageMetadataRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageCdnServiceCloudinaryImpl implements ImageCdnService {

    private final Cloudinary cloudinary;

    private final ImageMetadataRepository imageMetadataRepository;

    private final ImageResizeService imageResizeService;

    @Override
    public String getUrlByUUID(UUID uuid) {
        return imageMetadataRepository.findById(uuid)
                .orElseThrow(ImageNotFoundException::new)
                .getUrl();
    }

    @Override
    public ImageMetadataEntity upload(MultipartFile image) {
        try {
            return uploadToCloudinaryCdn(image.getInputStream(), 0, 0);
        } catch (IOException exception) {
            log.error("Error uploading image to cloudinary", exception);
            throw new ImageUploadException();
        }
    }

    @Override
    public ImageMetadataEntity upload(byte[] image, int height, int width) {
        try {
            return uploadToCloudinaryCdn(
                    imageResizeService.resize(new ByteArrayInputStream(image), height, width), height, width);
        } catch (Exception exception) {
            log.error("Error uploading image to cloudinary", exception);
            throw new ImageUploadException();
        }
    }

    @Override
    public ImageMetadataEntity uploadResizedImage(MultipartFile image, int height, int width) {
        try {
            return uploadToCloudinaryCdn(
                    imageResizeService.resize(image.getInputStream(), height, width), height, width);
        } catch (IOException exception) {
            log.error("Error uploading image to cloudinary", exception);
            throw new ImageUploadException();
        }
    }


    @SneakyThrows
    private ImageMetadataEntity uploadToCloudinaryCdn(InputStream image, int height, int width) {
        String publicId = String.valueOf(UUID.randomUUID());
        cloudinary.uploader().upload(image.readAllBytes(), ObjectUtils.asMap("public_id", publicId));
        String url = cloudinary.url().generate(publicId);
        ImageMetadataEntity imageMetadata = ImageMetadataEntity.builder()
                .url(url)
                .height(height)
                .width(width)
                .build();
        return imageMetadataRepository.save(imageMetadata);
    }
}
