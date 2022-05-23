package com.technokratos.aiocars.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand")
public class BrandEntity extends AbstractEntity {

    private String name;

    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "brand")
    private List<CarEntity> cars;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "brand_image_metadata",
            joinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "image_metadata_id", referencedColumnName = "id")
    )
    private List<ImageMetadataEntity> images;

    public void addCar(CarEntity car){
        cars.add(car);
        car.setBrand(this);
    }

    public void addImage(ImageMetadataEntity image){
        images.add(image);
    }
}
