package com.technokratos.aiocars.api;

import com.technokratos.aiocars.dto.request.SubscriptionRequest;
import com.technokratos.aiocars.dto.response.SubscriptionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(tags = "subscription-api")
@RequestMapping("/api/v1/subscriptions")
public interface SubscriptionApi <T extends UserDetails> {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "Create new subscription")
    @ApiResponse(code = 201, message = "Subscription successfully created")
    SubscriptionResponse create(@AuthenticationPrincipal T userDetails,
                                @RequestBody SubscriptionRequest subscriptionRequest);


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Page<SubscriptionResponse> getUserSubscriptions(@AuthenticationPrincipal T userDetails, Pageable pageable);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    SubscriptionResponse getById(@PathVariable UUID id);
}
