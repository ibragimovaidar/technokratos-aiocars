package com.technokratos.aiocars.service.impl;

import com.technokratos.aiocars.dto.request.BrandRequest;
import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.response.BrandResponse;
import com.technokratos.aiocars.exception.BrandNotFoundException;
import com.technokratos.aiocars.exception.CarNotFoundException;
import com.technokratos.aiocars.exception.ImageNotFoundException;
import com.technokratos.aiocars.model.BrandEntity;
import com.technokratos.aiocars.model.ImageMetadataEntity;
import com.technokratos.aiocars.repository.BrandRepository;
import com.technokratos.aiocars.repository.CarRepository;
import com.technokratos.aiocars.repository.ImageMetadataRepository;
import com.technokratos.aiocars.service.BrandService;
import com.technokratos.aiocars.util.mapper.BrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final CarRepository carRepository;

    private final BrandMapper brandMapper;

    private final ImageMetadataRepository imageMetadataRepository;

    @Override
    public BrandResponse create(BrandRequest brandRequest) {
        BrandEntity brand = brandMapper.toEntity(brandRequest);
        if (Objects.nonNull(brand.getImages())) {
            List<ImageMetadataEntity> images = brandRequest.getImages().stream()
                    .map(imageRequest -> imageMetadataRepository
                            .findById(imageRequest.getId())
                            .orElseThrow(ImageNotFoundException::new))
                    .collect(Collectors.toList());
            brand.setImages(images);
        }
        return brandMapper.toResponse(brandRepository.save(brand));
    }

    @Override
    public Page<BrandResponse> getAll(Pageable pageable) {
        return brandRepository.findAll(pageable).map(brandMapper::toResponse);
    }

    @Override
    public BrandResponse getById(UUID id) {
        return brandMapper.toResponse(
                brandRepository.findById(id).orElseThrow(BrandNotFoundException::new));
    }

    @Override
    public BrandResponse update(UUID id, BrandRequest brandRequest) {
        BrandEntity brand = brandRepository.findById(id).orElseThrow(BrandNotFoundException::new);
        if (Objects.nonNull(brandRequest.getCars())) {
            brandRequest.getCars().stream()
                    .map(carRequest -> carRepository.findById(carRequest.getId())
                            .orElseThrow(CarNotFoundException::new))
                    .forEach(brand::addCar);
        }
        if (Objects.nonNull(brandRequest.getImages())) {
            brandRequest.getImages().stream()
                    .map(imageRequest -> imageMetadataRepository
                            .findById(imageRequest.getId())
                            .orElseThrow(ImageNotFoundException::new))
                    .forEach(brand::addImage);
        }
        return brandMapper.toResponse(brandRepository.save(brand));
    }

    @Override
    public BrandResponse addImage(UUID id, LightImageRequest imageRequest) {
        BrandEntity brand = brandRepository.findById(id).orElseThrow(BrandNotFoundException::new);
        brand.addImage(imageMetadataRepository.findById(imageRequest.getId())
                .orElseThrow(ImageNotFoundException::new));
        return brandMapper.toResponse(brandRepository.save(brand));
    }
}
