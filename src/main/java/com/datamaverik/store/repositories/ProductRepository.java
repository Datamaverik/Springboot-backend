package com.datamaverik.store.repositories;

import com.datamaverik.store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
