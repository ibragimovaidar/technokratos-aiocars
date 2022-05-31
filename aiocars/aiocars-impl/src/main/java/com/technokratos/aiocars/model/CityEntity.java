package com.technokratos.aiocars.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.locationtech.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Entity
@Table(name = "d_city")
public class CityEntity extends AbstractEntity {

    private String name;

    private String description;

    private Double lat;

    private Double lon;

    @Column(columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point position;
}
