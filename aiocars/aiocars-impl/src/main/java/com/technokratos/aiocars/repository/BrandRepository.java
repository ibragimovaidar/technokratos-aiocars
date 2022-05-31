package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.model.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<BrandEntity, UUID> {
}
