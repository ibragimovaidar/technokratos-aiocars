package com.technokratos.aiocars.model;

import com.technokratos.aiocars.dto.enums.AdvertisementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Entity
@Table(name = "advertisement")
public class AdvertisementEntity extends AbstractEntity {

    private String name;

    private String description;

    @Column(unique = true)
    private String url;

    private Integer mileage;

    private Integer year;

    private Long price;

    @Enumerated(EnumType.STRING)
    private AdvertisementType type;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;
}
