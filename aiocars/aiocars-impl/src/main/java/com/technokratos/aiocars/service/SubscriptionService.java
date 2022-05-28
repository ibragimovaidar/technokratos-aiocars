package com.technokratos.aiocars.service;

import com.technokratos.aiocars.dto.request.SubscriptionRequest;
import com.technokratos.aiocars.dto.response.SubscriptionResponse;
import com.technokratos.aiocars.security.userdetails.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SubscriptionService {

    SubscriptionResponse create(UserAccount userAccount, SubscriptionRequest subscriptionRequest);

    SubscriptionResponse getById(UUID id);

    Page<SubscriptionResponse> getUserSubscriptions(UUID id, Pageable pageable);
}
