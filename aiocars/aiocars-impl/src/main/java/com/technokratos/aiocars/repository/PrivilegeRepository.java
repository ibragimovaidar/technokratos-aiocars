package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.dto.enums.Privilege;
import com.technokratos.aiocars.model.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, UUID> {

    PrivilegeEntity findByPrivilege(Privilege privilege);

    Set<PrivilegeEntity> findByPrivilegeIsIn(Set<Privilege> privileges);
}

