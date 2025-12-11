package com.datamaverik.store.services;

import com.datamaverik.store.entities.Category;
import com.datamaverik.store.entities.Product;
import com.datamaverik.store.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void addCategory() {
        var product = Product.builder()
                .name("PS5")
                .price(BigDecimal.valueOf(65000.00))
                .description("This is a description")
                .build();

        var category =  new Category("electronics");

        category.addProduct(product);

        categoryRepository.save(category);
    }
}
