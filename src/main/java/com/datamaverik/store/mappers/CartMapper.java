package com.datamaverik.store.mappers;

import com.datamaverik.store.dtos.CartDto;
import com.datamaverik.store.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
}
