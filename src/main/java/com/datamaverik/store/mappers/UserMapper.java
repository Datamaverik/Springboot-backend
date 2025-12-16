package com.datamaverik.store.mappers;

import com.datamaverik.store.dtos.RegisterUserRequest;
import com.datamaverik.store.dtos.UpdateUserRequest;
import com.datamaverik.store.dtos.UserDto;
import com.datamaverik.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);

    User toEntity(RegisterUserRequest request);

    void update(UpdateUserRequest request, @MappingTarget User user);
}
