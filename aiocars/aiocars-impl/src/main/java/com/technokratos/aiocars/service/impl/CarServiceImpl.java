package com.technokratos.aiocars.service.impl;

import com.technokratos.aiocars.dto.request.CarRequest;
import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.response.CarResponse;
import com.technokratos.aiocars.exception.BadRequestException;
import com.technokratos.aiocars.exception.BrandNotFoundException;
import com.technokratos.aiocars.exception.CarNotFoundException;
import com.technokratos.aiocars.exception.ImageNotFoundException;
import com.technokratos.aiocars.model.BrandEntity;
import com.technokratos.aiocars.model.CarEntity;
import com.technokratos.aiocars.model.ImageMetadataEntity;
import com.technokratos.aiocars.repository.BrandRepository;
import com.technokratos.aiocars.repository.CarRepository;
import com.technokratos.aiocars.repository.ImageMetadataRepository;
import com.technokratos.aiocars.service.CarService;
import com.technokratos.aiocars.util.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final BrandRepository brandRepository;

    private final ImageMetadataRepository imageMetadataRepository;

    private final CarMapper carMapper;

    @Transactional
    @Override
    public CarResponse create(CarRequest carRequest) {
        CarEntity car = carMapper.toEntity(carRequest);
        BrandEntity brand  = Optional.ofNullable(carRequest.getBrand())
                .map(brandRequest -> brandRepository.findById(brandRequest.getId()).orElseThrow(BrandNotFoundException::new))
                .orElseThrow(() -> new BadRequestException("Car's brand must be provided"));
        brand.addCar(car);
        return carMapper.toResponse(carRepository.save(car));
    }

    @Override
    public Page<CarResponse> getAll(Pageable pageable) {
        return carRepository.findAll(pageable).map(carMapper::toResponse);
    }

    @Override
    public CarResponse getById(UUID id) {
        return carMapper.toResponse(carRepository.findById(id).orElseThrow(CarNotFoundException::new));
    }

    @Override
    public Page<CarResponse> getAllByBrandId(UUID brandId, Pageable pageable) {
        return carRepository.findAllByBrandId(brandId, pageable).map(carMapper::toResponse);
    }

    @Override
    public CarResponse update(UUID id, CarRequest carRequest) {
        // TODO
        return null;
    }

    @Override
    public CarResponse addImage(UUID id, LightImageRequest imageRequest) {
        CarEntity car = carRepository.findById(id).orElseThrow(CarNotFoundException::new);
        ImageMetadataEntity image = Optional.ofNullable(imageRequest.getId())
                .map(imageId -> imageMetadataRepository.findById(imageId)
                        .orElseThrow(ImageNotFoundException::new))
                .orElseThrow(() -> new BadRequestException("Image must be provided"));
        car.addImage(image);
        return carMapper.toResponse(carRepository.save(car));
    }
}
