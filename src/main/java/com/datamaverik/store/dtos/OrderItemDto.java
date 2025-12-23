package com.datamaverik.store.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private OrderProductDto product;
    private BigDecimal totalPrice;
    private Integer quantity;
}
