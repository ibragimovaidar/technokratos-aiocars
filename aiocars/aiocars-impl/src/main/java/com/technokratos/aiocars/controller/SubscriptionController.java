package com.technokratos.aiocars.controller;

import com.technokratos.aiocars.api.SubscriptionApi;
import com.technokratos.aiocars.dto.request.SubscriptionRequest;
import com.technokratos.aiocars.dto.response.SubscriptionResponse;
import com.technokratos.aiocars.security.userdetails.UserAccount;
import com.technokratos.aiocars.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class SubscriptionController implements SubscriptionApi<UserAccount> {

    private final SubscriptionService subscriptionService;

    @Override
    public SubscriptionResponse create(UserAccount userAccount, SubscriptionRequest subscriptionRequest) {
        return subscriptionService.create(userAccount, subscriptionRequest);
    }

    @Override
    public Page<SubscriptionResponse> getUserSubscriptions(UserAccount userAccount, Pageable pageable) {
        return subscriptionService.getUserSubscriptions(userAccount.getId(), pageable);
    }

    @Override
    public SubscriptionResponse getById(UUID id) {
        return subscriptionService.getById(id);
    }
}
