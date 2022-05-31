package com.technokratos.aiocars.util.mapper;

import com.technokratos.aiocars.dto.response.PrivilegeResponse;
import com.technokratos.aiocars.model.PrivilegeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PrivilegeMapper {

    @Mapping(target = "privilegeDescription", source = "privilege.description")
    PrivilegeResponse toResponse(PrivilegeEntity privilege);

    List<PrivilegeResponse> toResponse(List<PrivilegeEntity> privileges);
}
