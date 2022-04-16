package com.technokratos.aiocars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SubscriptionEntity extends AbstractEntity {

    private String city;

    private Integer radius;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "min_mileage")
    private Integer minMileage;

    @Column(name = "max_mileage")
    private Integer maxMileage;

    @Column(name = "min_price")
    private Long minPrice;

    @Column(name = "max_price")
    private Long maxPrice;

    @Column(name = "min_year")
    private Integer minYear;

    @Column(name = "max_year")
    private Integer maxYear;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;
}
