package com.datamaverik.store.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDto {
    @NotNull
    private Long orderId;
}
