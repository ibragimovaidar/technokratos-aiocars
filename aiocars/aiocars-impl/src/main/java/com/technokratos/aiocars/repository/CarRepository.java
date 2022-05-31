package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.dto.response.CarResponse;
import com.technokratos.aiocars.model.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {

    @Query("SELECT car FROM CarEntity car WHERE car.brand.id = :brandId")
    Page<CarEntity> findAllByBrandId(UUID brandId, Pageable pageable);
}
