package com.technokratos.aiocars.model;

import com.technokratos.aiocars.dto.enums.SubscriptionType;
import com.technokratos.aiocars.model.embedded.SubscriptionFilter;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "subscription")
public class SubscriptionEntity extends AbstractEntity {

    private String target;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SubscriptionType type;

    @Builder.Default
    @Column(name = "is_active")
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityEntity city;

    @Embedded
    private SubscriptionFilter filter;
}
