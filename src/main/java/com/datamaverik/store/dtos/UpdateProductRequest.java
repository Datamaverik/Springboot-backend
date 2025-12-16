package com.datamaverik.store.dtos;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private String name;
    private String description;
    private Float price;
}
