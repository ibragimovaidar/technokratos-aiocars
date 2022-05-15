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

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Collection<AdvertisementEntity> grabAdvertisements(CarEntity car, AdvertisementFilterRequest filter) {
        String baseUrl = buildAutoUrlWithoutGeo(car);
        return null;
    }

    private static final String AUTO_RU_URL_BASE = "http://auto.ru/cars/%s/%s/all/?sort=cr_date-desc";

    private static final String AUTO_RU_URL_GEO_SUGGEST = "http://auto.ru/-/ajax/desktop/getGeoSuggest/";

    private String buildAutoUrlWithoutGeo(CarEntity car){
        return String.format(AUTO_RU_URL_BASE, car.getBrand().getName(), car.getParserData().getAutoruSlug());
    }

    public int grabAutoRuGeoId(AdvertisementFilterRequest filterRequest){
        GeoSuggestDto geoSuggestRequest = GeoSuggestDto.builder()
                .letters(filterRequest.getCity())
                .build();
        GeoSuggestDto geoSuggestDto =
                restTemplate.postForEntity(AUTO_RU_URL_GEO_SUGGEST, geoSuggestRequest, GeoSuggestDto.class).getBody();
        return Integer.parseInt(geoSuggestDto.getGeoId());
    }
}
