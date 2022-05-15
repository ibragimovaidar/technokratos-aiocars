package com.technokratos.aiocars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "car")
public class CarEntity extends AbstractEntity {

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @Embedded
    private CarParserData parserData;

    @ToString.Exclude
    @OneToMany
    private List<AdvertisementEntity> advertisements;
}
