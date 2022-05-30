package com.technokratos.aiocars.api;

import com.technokratos.aiocars.dto.request.ImageMetadataRequest;
import com.technokratos.aiocars.dto.response.ImageMetadataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Api(tags = "image-api")
@RequestMapping("/api/v1/images")
public interface ImageApi {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "Upload image")
    @ApiResponse(code = 201, message = "Image successfully uploaded")
    ImageMetadataResponse upload(@RequestBody ImageMetadataRequest imageMetadataRequest);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "Upload image")
    @ApiResponse(code = 201, message = "Image successfully uploaded")
    ImageMetadataResponse uploadMultipartFile(MultipartFile image,
                                 @RequestParam(value = "w", required = false) Integer width,
                                 @RequestParam(value = "h", required = false) Integer height);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get image by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Image with given id not found")
    })
    ImageMetadataResponse getById(@PathVariable UUID id);
}
