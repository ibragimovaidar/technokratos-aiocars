package com.technokratos.aiocars.repository;


import com.technokratos.aiocars.model.AdvertisementEntity;
import com.technokratos.aiocars.model.SubscriptionEntity;

import java.util.List;

public interface SubscriptionsSearchRepository {

    List<SubscriptionEntity> findAllMatchingAdvertisement(AdvertisementEntity advertisement);
}
