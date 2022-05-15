package com.technokratos.aiocars.model;

import com.technokratos.aiocars.dto.enums.Privilege;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "privileges")
public class PrivilegeEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Privilege privilege;
}
