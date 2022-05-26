package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.model.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, UUID> {
}
