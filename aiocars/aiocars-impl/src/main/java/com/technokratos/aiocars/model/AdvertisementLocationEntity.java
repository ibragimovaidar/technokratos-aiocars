package com.technokratos.aiocars.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "advertisement_location")
public class AdvertisementLocationEntity extends AbstractEntity {

    private Double lat;

    private Double lon;

    @Column(columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point position;

    @OneToOne
    @JoinColumn(name = "advertisement_id", referencedColumnName = "id")
    private AdvertisementEntity advertisement;
}
