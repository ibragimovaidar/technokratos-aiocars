package com.technokratos.aiocars.service;

import com.technokratos.aiocars.dto.request.CarRequest;
import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.response.CarResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CarService {
    CarResponse create(CarRequest carRequest);

    Page<CarResponse> getAll(Pageable pageable);

    CarResponse getById(UUID id);

    Page<CarResponse> getAllByBrandId(UUID brandId, Pageable pageable);

    CarResponse update(UUID id, CarRequest carRequest);

    CarResponse addImage(UUID id, LightImageRequest imageRequest);
}
