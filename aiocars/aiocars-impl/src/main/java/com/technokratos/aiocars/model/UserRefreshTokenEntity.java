package com.technokratos.aiocars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "account_refresh_token")
public class UserRefreshTokenEntity extends RefreshTokenEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    private UserEntity userEntity;
}
