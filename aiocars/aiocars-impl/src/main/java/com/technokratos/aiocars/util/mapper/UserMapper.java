package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.request.UserRegisterRequest;
import com.technokratos.aiocars.dto.response.LightUserResponse;
import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = RoleMapper.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "hashPassword", source = "password")
    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    UserEntity toEntity(UserRegisterRequest userRegisterRequest);

    UserResponse toResponse(UserEntity userEntity);

    LightUserResponse toLightResponse(UserEntity userEntity);
}
