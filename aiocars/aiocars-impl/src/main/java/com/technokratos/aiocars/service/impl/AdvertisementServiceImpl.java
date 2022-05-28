package com.technokratos.aiocars.service.impl;

import com.technokratos.aiocars.dto.enums.AdvertisementType;
import com.technokratos.aiocars.dto.request.AdvertisementRequest;
import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import com.technokratos.aiocars.exception.AdvertisementNotFoundException;
import com.technokratos.aiocars.exception.CarNotFoundException;
import com.technokratos.aiocars.exception.CityNotFoundException;
import com.technokratos.aiocars.exception.UserNotFoundException;
import com.technokratos.aiocars.model.*;
import com.technokratos.aiocars.repository.*;
import com.technokratos.aiocars.service.AdvertisementService;
import com.technokratos.aiocars.service.scheduling.SubscriptionTaskManager;
import com.technokratos.aiocars.util.mapper.AdvertisementMapper;
import com.technokratos.aiocars.util.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    private final AdvertisementLocationRepository advertisementLocationRepository;

    private final CarRepository carRepository;

    private final CityRepository cityRepository;

    private final ImageMetadataRepository imageMetadataRepository;

    private final UserRepository userRepository;

    private final AdvertisementMapper advertisementMapper;

    private final CarMapper carMapper;

    private final GeometryFactory geometryFactory;

    private final SubscriptionTaskManager subscriptionTaskManager;

    @Override
    public AdvertisementResponse create(UUID userId, AdvertisementRequest advertisementRequest) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        CarEntity car = carRepository.findById(advertisementRequest.getCarId()).orElseThrow(CarNotFoundException::new);
        List<ImageMetadataEntity> images = new ArrayList<>();
        if (Objects.nonNull(advertisementRequest.getImages())){
            images = imageMetadataRepository.findAllById(
                    advertisementRequest.getImages().stream()
                            .map(LightImageRequest::getId)
                            .collect(Collectors.toList()));
        }
        AdvertisementLocationEntity advertisementLocation = advertisementLocationRepository.save(
                AdvertisementLocationEntity.builder()
                        .lat(advertisementRequest.getLocation().getLat())
                        .lon(advertisementRequest.getLocation().getLon())
                        .position(geometryFactory.createPoint(new Coordinate(
                                advertisementRequest.getLocation().getLon(),
                                advertisementRequest.getLocation().getLat())))
                        .build());
        // get the nearest city to provided location
        CityEntity city = cityRepository.findNearestCityByPoint(
                        geometryFactory.createPoint(new Coordinate(
                                advertisementRequest.getLocation().getLon(),
                                advertisementRequest.getLocation().getLat())))
                .orElseThrow(CityNotFoundException::new);
        AdvertisementEntity advertisement = AdvertisementEntity.builder()
                .name(advertisementRequest.getName())
                .description(advertisementRequest.getDescription())
                .location(advertisementLocation)
                .images(images)
                .type(AdvertisementType.AIOCARS)
                .mileage(advertisementRequest.getMileage())
                .price(advertisementRequest.getPrice())
                .year(advertisementRequest.getYear())
                .isActive(true)
                .car(car)
                .city(city)
                .user(user)
                .build();
        AdvertisementResponse response = advertisementMapper.toResponse(advertisementRepository.save(advertisement));
        subscriptionTaskManager.handleSubscriptions(response, carMapper.toResponse(car));
        return response;
    }

    @Override
    public Page<AdvertisementResponse> getAll(Pageable pageable) {
        return advertisementRepository.findAll(pageable).map(advertisementMapper::toResponse);
    }

    @Override
    public AdvertisementResponse getById(UUID id) {
        return advertisementMapper.toResponse(
                advertisementRepository.findById(id).orElseThrow(AdvertisementNotFoundException::new));
    }
}
