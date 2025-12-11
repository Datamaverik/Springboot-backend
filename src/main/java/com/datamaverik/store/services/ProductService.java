package com.datamaverik.store.services;

import com.datamaverik.store.entities.Product;
import com.datamaverik.store.repositories.CategoryRepository;
import com.datamaverik.store.repositories.ProductRepository;
import com.datamaverik.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addProduct() {
        var category = categoryRepository.findById(3).orElseThrow();

        var product = Product.builder()
                .name("iPhone")
                .price(BigDecimal.valueOf(175000.00))
                .description("This is a description")
                .build();

        category.addProduct(product);

        categoryRepository.save(category);
    }

    @Transactional
    public void removeProduct() {
        productRepository.deleteById(3);
    }
}
