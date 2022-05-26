package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.model.CityEntity;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CityRepository extends JpaRepository<CityEntity, UUID> {

    //TODO nearest city by point
    @Query(
            value = "select c.id, c.lat, c.lon, c.name, c.description, c.position, c.create_date, c.update_date, " +
                    "round(cast(st_distancesphere(position, :point) as numeric), 2) as distance " +
                    "from d_city c order by distance limit 1",
            nativeQuery = true)
    Optional<CityEntity> findNearestCityByPoint(@Param("point") Point point);
}
