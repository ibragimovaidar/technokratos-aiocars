package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findOneByUsername(String username);
}
