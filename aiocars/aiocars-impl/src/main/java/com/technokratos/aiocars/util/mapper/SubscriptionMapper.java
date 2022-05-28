package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.request.SubscriptionFilterRequest;
import com.technokratos.aiocars.dto.request.SubscriptionRequest;
import com.technokratos.aiocars.dto.response.SubscriptionFilterResponse;
import com.technokratos.aiocars.dto.response.SubscriptionResponse;
import com.technokratos.aiocars.model.SubscriptionEntity;
import com.technokratos.aiocars.model.embedded.SubscriptionFilter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class,
                CarMapper.class,
                CityMapper.class})
public interface SubscriptionMapper {

    @Mapping(target = "car", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "city", ignore = true)
    SubscriptionEntity toEntity(SubscriptionRequest subscriptionRequest);

    SubscriptionResponse toResponse(SubscriptionEntity subscriptionEntity);

    SubscriptionFilter toSubscriptionFilter(SubscriptionFilterRequest subscriptionFilterRequest);

    SubscriptionFilterResponse toSubscriptionFilterResponse(SubscriptionFilter subscriptionFilter);
}
