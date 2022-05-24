package com.technokratos.aiocars.service;

import com.technokratos.aiocars.dto.request.BrandRequest;
import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.response.BrandResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BrandService {

    BrandResponse create(BrandRequest brandRequest);

    Page<BrandResponse> getAll(Pageable pageable);

    BrandResponse getById(UUID id);

    BrandResponse update(UUID id, BrandRequest brandRequest);

    BrandResponse addImage(UUID id, LightImageRequest imageRequest);
}
