package com.technokratos.aiocars.controller;

import com.technokratos.aiocars.api.BrandApi;
import com.technokratos.aiocars.dto.request.BrandRequest;
import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.response.BrandResponse;
import com.technokratos.aiocars.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class BrandController implements BrandApi {

    private final BrandService brandService;

    @Override
    public BrandResponse create(BrandRequest brandRequest) {
        return brandService.create(brandRequest);
    }

    @Override
    public Page<BrandResponse> getAll(Pageable pageable) {
        return brandService.getAll(pageable);
    }

    @Override
    public BrandResponse getById(UUID id) {
        return brandService.getById(id);
    }

    @Override
    public BrandResponse update(UUID id, BrandRequest brandRequest) {
        return brandService.update(id, brandRequest);
    }

    @Override
    public BrandResponse addImage(UUID id, LightImageRequest imageRequest) {
        return brandService.addImage(id, imageRequest);
    }
}
