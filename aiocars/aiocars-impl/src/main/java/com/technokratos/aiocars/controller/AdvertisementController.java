package com.technokratos.aiocars.controller;

import com.technokratos.aiocars.api.AdvertisementApi;
import com.technokratos.aiocars.dto.request.AdvertisementByLocationRequest;
import com.technokratos.aiocars.dto.request.AdvertisementRequest;
import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import com.technokratos.aiocars.security.userdetails.UserAccount;
import com.technokratos.aiocars.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class AdvertisementController implements AdvertisementApi<UserAccount> {

    private final AdvertisementService advertisementService;

    @Override
    public AdvertisementResponse create(UserAccount userAccount, AdvertisementRequest advertisementRequest) {
        return advertisementService.create(userAccount.getId(), advertisementRequest);
    }

    @Override
    public Page<AdvertisementResponse> getAll(Pageable pageable) {
        return advertisementService.getAll(pageable);
    }

    @Override
    public AdvertisementResponse getById(UUID id) {
        return advertisementService.getById(id);
    }

    @Override
    public Page<AdvertisementResponse> getAllByCarId(UUID carId, Pageable pageable) {
        return advertisementService.getAllByCarId(carId, pageable);
    }

    @Override
    public List<AdvertisementResponse> getAllInRadiusByLocation(AdvertisementByLocationRequest advertisementByLocation) {
        return advertisementService.getAllInRadiusByLocation(advertisementByLocation);
    }

    @Override
    public Page<AdvertisementResponse> getAllByCityId(UUID cityId, Pageable pageable) {
        return advertisementService.getAllByCityId(cityId, pageable);
    }
}
