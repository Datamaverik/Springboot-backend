package com.datamaverik.store.controllers;

import com.datamaverik.store.dtos.ProductDto;
import com.datamaverik.store.dtos.RegisterProductRequest;
import com.datamaverik.store.mappers.ProductMapper;
import com.datamaverik.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

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
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody RegisterProductRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var product = productMapper.toEntity(request);
        productRepository.save(product);

        var productDto = productMapper.toDto(product);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }
}
