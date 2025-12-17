package com.datamaverik.store.repositories;

import com.datamaverik.store.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID>, JpaSpecificationExecutor<Cart> {
}