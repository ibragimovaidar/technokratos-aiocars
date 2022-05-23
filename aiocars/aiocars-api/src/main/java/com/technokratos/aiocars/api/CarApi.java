package com.technokratos.aiocars.api;

import com.technokratos.aiocars.dto.request.CarRequest;
import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.response.CarResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/cars")
public interface CarApi {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CarResponse create(@RequestBody CarRequest carRequest);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Page<CarResponse> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    CarResponse getById(@PathVariable("id") UUID id);

    @GetMapping("/brand/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Page<CarResponse> getAllByBrandId(@PathVariable("brandId") UUID brandId, Pageable pageable);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    CarResponse update(@PathVariable("id") UUID id, @RequestBody CarRequest carRequest);

    @PostMapping("/{id}/images")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    CarResponse addImage(@PathVariable("id") UUID id, @RequestBody LightImageRequest imageRequest);
}
