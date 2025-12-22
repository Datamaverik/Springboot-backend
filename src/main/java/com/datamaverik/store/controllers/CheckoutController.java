package com.datamaverik.store.controllers;

import com.datamaverik.store.dtos.CheckoutRequest;
import com.datamaverik.store.dtos.ErrorDto;
import com.datamaverik.store.dtos.OrderDto;
import com.datamaverik.store.entities.Order;
import com.datamaverik.store.entities.OrderItem;
import com.datamaverik.store.entities.OrderStatus;
import com.datamaverik.store.exceptions.CartNotFoundException;
import com.datamaverik.store.exceptions.ProductNotFoundException;
import com.datamaverik.store.repositories.CartRepository;
import com.datamaverik.store.repositories.ProductRepository;
import com.datamaverik.store.repositories.UserRepository;
import com.datamaverik.store.services.CheckoutService;
import com.datamaverik.store.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<OrderDto> getOrder(
            @Valid @RequestBody CheckoutRequest request
    ) {
        var orderDto = checkoutService.createOrder(request.getCartId());

        return ResponseEntity.ok(orderDto);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCartNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto("Cart not found or Cart is empty")
        );
    }
}
