package com.technokratos.aiocars.api;

import com.technokratos.aiocars.dto.request.AdvertisementRequest;
import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(tags = "advertisements-api")
@RequestMapping("/api/v1/advertisements")
public interface AdvertisementApi <T extends UserDetails> {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    AdvertisementResponse create(@AuthenticationPrincipal T userDetails, @RequestBody AdvertisementRequest advertisementRequest);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Page<AdvertisementResponse> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    AdvertisementResponse getById(@PathVariable UUID id);
}
