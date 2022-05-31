package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.dto.response.AdvertisementResponse;
import com.technokratos.aiocars.model.AdvertisementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, UUID> {

    Page<AdvertisementEntity> findAllByCarId(UUID carId, Pageable pageable);

    Page<AdvertisementEntity> findAllByCityId(UUID cityId, Pageable pageable);
}
