package com.datamaverik.store.controllers;

import com.datamaverik.store.dtos.ProductDto;
import com.datamaverik.store.dtos.RegisterProductRequest;
import com.datamaverik.store.dtos.UpdateProductRequest;
import com.datamaverik.store.mappers.ProductMapper;
import com.datamaverik.store.repositories.CategoryRepository;
import com.datamaverik.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestParam(required = false, defaultValue = "", name = "categoryId")
            Byte categoryId
    ) {
        if(categoryId == null)
            return productRepository.findAll()
                    .stream().map(productMapper::toDto)
                    .toList();

        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(
            @RequestBody RegisterProductRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var product = productMapper.toEntity(request);
        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid category");

        product.setCategory(category);
        productRepository.save(product);
        var productDto = productMapper.toDto(product);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateProductRequest request
    ) {
        var product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();

        productMapper.update(request, product);
        productRepository.save(product);

        return ResponseEntity.ok(productMapper.toDto(product));
    }
}
