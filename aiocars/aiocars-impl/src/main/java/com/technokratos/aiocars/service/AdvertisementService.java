package com.technokratos.aiocars.service;

import com.technokratos.aiocars.dto.LocationDto;
import com.technokratos.aiocars.dto.request.AdvertisementByLocationRequest;
import com.technokratos.aiocars.dto.request.AdvertisementRequest;
import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AdvertisementService {

    AdvertisementResponse create(UUID userId, AdvertisementRequest advertisementRequest);

    Page<AdvertisementResponse> getAll(Pageable pageable);

    AdvertisementResponse getById(UUID id);

    Page<AdvertisementResponse> getAllByCarId(UUID carId, Pageable pageable);

    List<AdvertisementResponse> getAllInRadiusByLocation(AdvertisementByLocationRequest advertisementByLocationRequest);

    List<AdvertisementResponse> getAllInRadiusByCity(UUID cityId, Integer radius);

    Page<AdvertisementResponse> getAllByCityId(UUID cityId, Pageable pageable);
}
