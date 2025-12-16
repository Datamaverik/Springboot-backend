package com.datamaverik.store.controllers;

import com.datamaverik.store.dtos.ProductDto;
import com.datamaverik.store.dtos.RegisterProductRequest;
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
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto request,
            UriComponentsBuilder uriBuilder
    ) {
        var product = productMapper.toEntity(request);
        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null)
            return ResponseEntity.badRequest().build();

        product.setCategory(category);
        productRepository.save(product);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        request.setId(product.getId());

        return ResponseEntity.created(uri).body(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestBody ProductDto request
    ) {
        var product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();

        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null)
            return ResponseEntity.badRequest().build();

        productMapper.update(request, product);
        product.setCategory(category);
        productRepository.save(product);
        request.setId(product.getId());

        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id){
        var product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();

        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
