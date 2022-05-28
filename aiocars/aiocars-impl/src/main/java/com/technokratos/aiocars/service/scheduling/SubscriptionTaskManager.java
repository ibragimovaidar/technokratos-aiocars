package com.technokratos.aiocars.service.scheduling;

import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import com.technokratos.aiocars.dto.response.CarResponse;

public interface SubscriptionTaskManager {

    void handleSubscriptions(AdvertisementResponse advertisementResponse, CarResponse carResponse);
}
