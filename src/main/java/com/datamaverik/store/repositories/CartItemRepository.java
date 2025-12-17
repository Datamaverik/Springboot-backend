package com.datamaverik.store.repositories;

import com.datamaverik.store.dtos.CartItemDto;
import com.datamaverik.store.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItemDto findByProductId(Long productId);
}