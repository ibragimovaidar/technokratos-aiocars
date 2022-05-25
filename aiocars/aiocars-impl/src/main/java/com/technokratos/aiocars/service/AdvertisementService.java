package com.technokratos.aiocars.service;

import com.technokratos.aiocars.dto.request.AdvertisementRequest;
import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AdvertisementService {

    AdvertisementResponse create(UUID userId, AdvertisementRequest advertisementRequest);

    Page<AdvertisementResponse> getAll(Pageable pageable);

    AdvertisementResponse getById(UUID id);
}
