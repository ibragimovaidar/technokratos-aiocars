package com.technokratos.aiocars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public class RefreshTokenEntity extends AbstractEntity {

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;
}
