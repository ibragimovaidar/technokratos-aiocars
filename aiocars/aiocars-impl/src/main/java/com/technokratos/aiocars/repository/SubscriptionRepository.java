package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.model.SubscriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID>{

    Page<SubscriptionEntity> findAllByUserId(UUID user_id, Pageable pageable);

    @Query(value = "SELECT s FROM SubscriptionEntity s " +
            "WHERE s.car.id = :carId " +
            "AND s.city.id = :cityId " +
            "AND s.filter.minMileage <= :mileage " +
            "AND s.filter.maxMileage >= :mileage " +
            "AND s.filter.minYear <= :year " +
            "AND s.filter.maxYear >= :year " +
            "AND s.filter.minPrice <= :price " +
            "AND s.filter.maxPrice >= :price")
    List<SubscriptionEntity> findAllMatchingAdvertisement(@Param("carId") UUID carId,
                                                          @Param("cityId") UUID cityId,
                                                          @Param("mileage") Integer mileage,
                                                          @Param("year") Integer year,
                                                          @Param("price") Long price);
}
