package com.technokratos.aiocars.model;

import com.technokratos.aiocars.model.embedded.CarParserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "car_image_metadata",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "image_metadata_id", referencedColumnName = "id")
    )
    private List<ImageMetadataEntity> images;

    public void addImage(ImageMetadataEntity image){
        images.add(image);
    }
}
