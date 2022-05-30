package com.technokratos.aiocars.api;

import com.technokratos.aiocars.dto.request.CarRequest;
import com.technokratos.aiocars.dto.request.LightImageRequest;
import com.technokratos.aiocars.dto.response.CarResponse;
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

@Api(tags = "car-api")
@RequestMapping("/api/v1/cars")
public interface CarApi {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "Create new car")
    @ApiResponse(code = 201, message = "Car successfully created")
    CarResponse create(@RequestBody CarRequest carRequest);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get cars page")
    Page<CarResponse> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get car by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Car with given id not found")
    })
    CarResponse getById(@PathVariable("id") UUID id);

    @GetMapping("/brand/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Brand with given id not found")
    })
    Page<CarResponse> getAllByBrandId(@PathVariable("brandId") UUID brandId, Pageable pageable);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Update car by id")
    @ApiResponse(code = 200, message = "Successfully updated")
    CarResponse update(@PathVariable("id") UUID id, @RequestBody CarRequest carRequest);

    @PostMapping("/{id}/images")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Add image to car by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image successfully added"),
            @ApiResponse(code = 404, message = "Car with given id not found")
    })
    CarResponse addImage(@PathVariable("id") UUID id, @RequestBody LightImageRequest imageRequest);
}
