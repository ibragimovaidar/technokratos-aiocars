package com.technokratos.aiocars.repository;

import com.technokratos.aiocars.model.UserRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshTokenEntity, UUID> {

    @Query("SELECT token FROM UserRefreshTokenEntity token WHERE token.userEntity.id = :accountId")
    List<UserRefreshTokenEntity> findAllByAccountId(UUID accountId);
}
