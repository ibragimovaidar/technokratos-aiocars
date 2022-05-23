package com.technokratos.aiocars.api;

import com.technokratos.aiocars.dto.request.ImageMetadataRequest;
import com.technokratos.aiocars.dto.response.ImageMetadataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequestMapping("/api/v1/images")
public interface ImageApi {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    ImageMetadataResponse upload(@RequestBody ImageMetadataRequest imageMetadataRequest);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    ImageMetadataResponse uploadMultipartFile(MultipartFile image,
                                 @RequestParam(value = "w", required = false) Integer width,
                                 @RequestParam(value = "h", required = false) Integer height);

    @GetMapping("/{id}")
    ImageMetadataResponse getById(@PathVariable UUID id);
}
