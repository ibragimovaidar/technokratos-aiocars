package com.technokratos.aiocars.controller;

import com.technokratos.aiocars.api.CarApi;
import com.technokratos.aiocars.dto.request.CarRequest;
import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.response.CarResponse;
import com.technokratos.aiocars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CarController implements CarApi {

    private final CarService carService;

    @Override
    public CarResponse create(CarRequest carRequest) {
        return carService.create(carRequest);
    }

    @Override
    public Page<CarResponse> getAll(Pageable pageable) {
        return carService.getAll(pageable);
    }

    @Override
    public CarResponse getById(UUID id) {
        return carService.getById(id);
    }

    @Override
    public Page<CarResponse> getAllByBrandId(UUID brandId, Pageable pageable) {
        return carService.getAllByBrandId(brandId, pageable);
    }

    @Override
    public CarResponse update(UUID id, CarRequest carRequest) {
        return carService.update(id, carRequest);
    }

    @Override
    public CarResponse addImage(UUID id, LightImageRequest imageRequest) {
        return carService.addImage(id, imageRequest);
    }
}
