package com.datamaverik.store.dtos;

import com.datamaverik.store.entities.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class GetOrderDto {
    private Long id;
    private OrderStatus status;
    private Set<OrderItemDto> items;
    private LocalDateTime createdAt;
}
