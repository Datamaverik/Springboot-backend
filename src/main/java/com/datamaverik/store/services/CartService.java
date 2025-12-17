package com.datamaverik.store.services;

import com.datamaverik.store.dtos.CartDto;
import com.datamaverik.store.dtos.CartItemDto;
import com.datamaverik.store.dtos.UpdateCartItemRequest;
import com.datamaverik.store.entities.Cart;
import com.datamaverik.store.exceptions.CartNotFoundException;
import com.datamaverik.store.exceptions.ProductNotFoundException;
import com.datamaverik.store.mappers.CartMapper;
import com.datamaverik.store.repositories.CartRepository;
import com.datamaverik.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private ProductRepository productRepository;

    public CartDto createCart() {
        var cart = new Cart();
        cartRepository.save(cart);

        return cartMapper.toDto(cart);
    }

    public CartItemDto addToCart(UUID cartId, Long productId) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null)
            throw new CartNotFoundException();

        var product = productRepository.findById(productId).orElse(null);
        if(product == null)
            throw new ProductNotFoundException();

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public Cart getCart(UUID cartId) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null)
            throw new CartNotFoundException();

        return cart;
    }

    public CartItemDto updateItem(
            UUID cartId,
            Long productId,
            UpdateCartItemRequest request) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null)
            throw new CartNotFoundException();

        var cartItem = cart.getItem(productId);
        if(cartItem == null)
            throw new ProductNotFoundException();

        cartItem.setQuantity(request.getQuantity());
        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public void deleteItem(UUID cartId, Long productId) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null)
            throw new CartNotFoundException();

        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    public void clearCart(UUID cartId) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null)
            throw new CartNotFoundException();

        cart.clearItems();
        cartRepository.save(cart);
    }
}
