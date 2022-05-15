package com.technokratos.aiocars.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
}
