package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.model.SubscriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {

    Page<SubscriptionEntity> findAllByUserId(UUID user_id, Pageable pageable);
}
