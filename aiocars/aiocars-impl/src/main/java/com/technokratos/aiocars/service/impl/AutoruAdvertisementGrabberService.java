package com.technokratos.aiocars.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.aiocars.dto.autoru.GeoSuggestDto;
import com.technokratos.aiocars.dto.request.AdvertisementFilterRequest;
import com.technokratos.aiocars.model.AdvertisementEntity;
import com.technokratos.aiocars.model.CarEntity;
import com.technokratos.aiocars.service.AdvertisementGrabberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class AutoruAdvertisementGrabberService implements AdvertisementGrabberService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Collection<AdvertisementEntity> grabAdvertisements(CarEntity car, AdvertisementFilterRequest filter) {
        String baseUrl = buildAutoUrl(car, filter);
        System.out.println(baseUrl);
        System.out.println(restTemplate.getForObject(baseUrl, String.class));
        return null;
    }

    private static final String AUTO_RU_URL_BASE = "https://auto.ru/%s/cars/%s/%s/all/?sort=cr_date-desc";


    private String buildAutoUrl(CarEntity car, AdvertisementFilterRequest filterRequest){
        return String.format(AUTO_RU_URL_BASE, filterRequest.getCity(), car.getBrand().getName(), car.getParserData().getAutoruSlug());
    }
}
