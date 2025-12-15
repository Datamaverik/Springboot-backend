package com.datamaverik.store.mappers;

import com.datamaverik.store.dtos.UserDto;
import com.datamaverik.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
