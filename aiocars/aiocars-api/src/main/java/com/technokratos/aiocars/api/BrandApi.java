package com.technokratos.aiocars.api;

import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.request.BrandRequest;
import com.technokratos.aiocars.dto.response.BrandResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/brands")
public interface BrandApi {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    BrandResponse create(@RequestBody BrandRequest brandRequest);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Page<BrandResponse> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    BrandResponse getById(@PathVariable UUID id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    BrandResponse update(@PathVariable UUID id, @RequestBody BrandRequest brandRequest);

    @PostMapping("/{id}/images")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    BrandResponse addImage(@PathVariable("id") UUID id, @RequestBody LightImageRequest imageRequest);
}
