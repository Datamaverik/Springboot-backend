package com.datamaverik.store.dtos;

import lombok.Data;

@Data
public class RegisterProductRequest {
    private String name;
    private String description;
    private Float price;
    private Byte categoryId;
}
