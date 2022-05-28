package com.technokratos.aiocars.service;

import com.technokratos.aiocars.dto.request.SubscriptionFilterRequest;
import com.technokratos.aiocars.model.AdvertisementEntity;
import com.technokratos.aiocars.model.CarEntity;

import java.util.Collection;

public interface AdvertisementGrabberService {

    Collection<AdvertisementEntity> grabAdvertisements(CarEntity car, SubscriptionFilterRequest filter);
}
