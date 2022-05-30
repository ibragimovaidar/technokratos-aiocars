package com.technokratos.aiocars.api;

import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.request.BrandRequest;
import com.technokratos.aiocars.dto.response.BrandResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(tags = "brands-api")
@RequestMapping("/api/v1/brands")
public interface BrandApi {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "Create new brand")
    @ApiResponse(code = 201, message = "Brand successfully created")
    BrandResponse create(@RequestBody BrandRequest brandRequest);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get brands page")
    Page<BrandResponse> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get brand by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Brand with given id not found")
    })
    BrandResponse getById(@PathVariable UUID id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Update brand by id")
    @ApiResponse(code = 200, message = "Successfully updated")
    BrandResponse update(@PathVariable UUID id, @RequestBody BrandRequest brandRequest);

    @PostMapping("/{id}/images")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Add image to brand by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image successfully added"),
            @ApiResponse(code = 404, message = "Brand with given id not found")
    })
    BrandResponse addImage(@PathVariable("id") UUID id, @RequestBody LightImageRequest imageRequest);
}
