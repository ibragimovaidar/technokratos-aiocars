package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.response.RoleResponse;
import com.technokratos.aiocars.model.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = PrivilegeMapper.class)
public interface RoleMapper {

    @Mapping(target = "roleDescription", source = "role.description")
    RoleResponse toResponse(RoleEntity role);

    List<RoleResponse> toResponse(List<RoleEntity> roles);
}
