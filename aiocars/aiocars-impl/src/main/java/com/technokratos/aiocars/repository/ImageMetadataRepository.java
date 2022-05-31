package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.model.ImageMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageMetadataRepository extends JpaRepository<ImageMetadataEntity, UUID> {
}
