package com.datamaverik.store.mappers;

import com.datamaverik.store.dtos.GetOrderDto;
import com.datamaverik.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    GetOrderDto toDto(Order order);
}
