package com.technokratos.aiocars.service.impl;

import com.technokratos.aiocars.dto.request.SubscriptionRequest;
import com.technokratos.aiocars.dto.response.SubscriptionResponse;
import com.technokratos.aiocars.exception.CarNotFoundException;
import com.technokratos.aiocars.exception.CityNotFoundException;
import com.technokratos.aiocars.exception.SubscriptionNotFoundException;
import com.technokratos.aiocars.exception.UserNotFoundException;
import com.technokratos.aiocars.model.CarEntity;
import com.technokratos.aiocars.model.CityEntity;
import com.technokratos.aiocars.model.SubscriptionEntity;
import com.technokratos.aiocars.model.UserEntity;
import com.technokratos.aiocars.repository.CarRepository;
import com.technokratos.aiocars.repository.CityRepository;
import com.technokratos.aiocars.repository.SubscriptionRepository;
import com.technokratos.aiocars.repository.UserRepository;
import com.technokratos.aiocars.security.userdetails.UserAccount;
import com.technokratos.aiocars.service.SubscriptionService;
import com.technokratos.aiocars.util.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final CityRepository cityRepository;

    private final SubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionResponse create(UserAccount userAccount, SubscriptionRequest subscriptionRequest) {
        SubscriptionEntity subscription = subscriptionMapper.toEntity(subscriptionRequest);
        UserEntity user = userRepository.findById(userAccount.getId()).orElseThrow(UserNotFoundException::new);
        CarEntity car = carRepository.findById(subscriptionRequest.getCarId()).orElseThrow(CarNotFoundException::new);
        CityEntity city = cityRepository.findById(subscriptionRequest.getCityId()).orElseThrow(CityNotFoundException::new);
        subscription.setCar(car);
        subscription.setUser(user);
        subscription.setCity(city);
        return subscriptionMapper.toResponse(subscriptionRepository.save(subscription));
    }

    @Override
    public SubscriptionResponse getById(UUID id) {
        return subscriptionMapper.toResponse(
                subscriptionRepository.findById(id)
                        .orElseThrow(SubscriptionNotFoundException::new));
    }

    @Override
    public Page<SubscriptionResponse> getUserSubscriptions(UUID id, Pageable pageable) {
        return subscriptionRepository.findAllByUserId(id, pageable)
                .map(subscriptionMapper::toResponse);
    }
}
