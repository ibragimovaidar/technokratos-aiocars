package com.technokratos.aiocars.model;

import com.technokratos.aiocars.dto.enums.AdvertisementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "advertisement_image_metadata",
            joinColumns = @JoinColumn(name = "advertisement_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "image_metadata_id", referencedColumnName = "id")
    )
    private List<ImageMetadataEntity> images;
}
