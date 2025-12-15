package com.datamaverik.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Long categoryId;
}
