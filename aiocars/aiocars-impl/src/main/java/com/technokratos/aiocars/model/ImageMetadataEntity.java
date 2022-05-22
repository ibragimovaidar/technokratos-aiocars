package com.technokratos.aiocars.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class ImageMetadataEntity extends AbstractEntity {

    @Column(nullable = false)
    private String url;

    private String name;

    private String description;

    private Integer height;

    private Integer width;
}
