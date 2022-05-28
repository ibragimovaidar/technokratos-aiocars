package com.technokratos.aiocars.model.embedded;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class SubscriptionFilter {

    private Integer radius;

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
}
