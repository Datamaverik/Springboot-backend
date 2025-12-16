package com.datamaverik.store.mappers;

import com.datamaverik.store.dtos.ProductDto;
import com.datamaverik.store.dtos.RegisterProductRequest;
import com.datamaverik.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    Product toEntity(RegisterProductRequest request);
}
