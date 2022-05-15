package com.technokratos.aiocars.controller;

import com.technokratos.aiocars.dto.request.AdvertisementFilterRequest;
import com.technokratos.aiocars.model.BrandEntity;
import com.technokratos.aiocars.model.CarEntity;
import com.technokratos.aiocars.model.CarParserData;
import com.technokratos.aiocars.service.impl.AutoruAdvertisementGrabberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
@RestController
public class TestController {

    private final AutoruAdvertisementGrabberService grabberService;

    @GetMapping
    public String test(){
        CarEntity car = CarEntity.builder()
                .brand(BrandEntity.builder()
                        .name("bmw")
                        .build())
                .parserData(CarParserData.builder()
                        .autoruSlug("")
                        .build())
                .build();
        AdvertisementFilterRequest filterRequest = AdvertisementFilterRequest.builder()
                .city("Казань")
                .cityRadius(500)
                .build();
        return String.valueOf(grabberService.grabAutoRuGeoId(filterRequest));
    }
}
