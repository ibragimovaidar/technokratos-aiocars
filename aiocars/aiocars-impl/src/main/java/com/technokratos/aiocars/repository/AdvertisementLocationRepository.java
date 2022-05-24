package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.model.AdvertisementLocationEntity;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AdvertisementLocationRepository extends JpaRepository<AdvertisementLocationEntity, UUID> {

    @Query(
            value = "select l.id, l.lat, l.lon, l.position, l.advertisement_id," +
                    "round(cast(st_distancesphere(position, :point) as numeric), 2) as distance " +
                    "from advertisement_location l order by distance asc limit :limit",
            nativeQuery = true)
    List<AdvertisementLocationEntity> findAllOrderedByDistance(@Param("point") Point point, int limit);
}
