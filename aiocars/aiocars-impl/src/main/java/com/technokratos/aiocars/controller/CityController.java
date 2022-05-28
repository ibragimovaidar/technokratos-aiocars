package com.technokratos.aiocars.controller;

import com.technokratos.aiocars.dto.request.CityRequest;
import com.technokratos.aiocars.dto.response.CityResponse;
import com.technokratos.aiocars.model.CityEntity;
import com.technokratos.aiocars.repository.CityRepository;
import com.technokratos.aiocars.util.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    private final CityRepository cityRepository;

    private final GeometryFactory geometryFactory;

    private final CityMapper cityMapper;

    @PostMapping
    @ResponseBody
    List<CityResponse> saveCities(@RequestBody List<CityRequest> cities){
        List<CityEntity> cityEntities = cities.stream()
                .map(cityRequest -> {
                    return CityEntity.builder()
                            .name(cityRequest.getName())
                            .description(cityRequest.getDescription())
                            .lat(cityRequest.getLat())
                            .lon(cityRequest.getLon())
                            .position(geometryFactory.createPoint(
                                    new Coordinate(
                                            cityRequest.getLon(),
                                            cityRequest.getLat())))
                            .build();
                })
                .collect(Collectors.toList());
        return cityRepository.saveAll(cityEntities).stream()
                .map(cityMapper::toResponse)
                .collect(Collectors.toList());
    }
}
