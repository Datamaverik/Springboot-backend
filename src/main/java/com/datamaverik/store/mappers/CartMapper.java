package com.datamaverik.store.mappers;

import com.datamaverik.store.dtos.CartDto;
import com.datamaverik.store.dtos.CartItemDto;
import com.datamaverik.store.entities.Cart;
import com.datamaverik.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}