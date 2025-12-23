package com.datamaverik.store.controllers;

import com.datamaverik.store.dtos.CheckoutRequest;
import com.datamaverik.store.dtos.ErrorDto;
import com.datamaverik.store.dtos.OrderDto;
import com.datamaverik.store.exceptions.CartNotFoundException;
import com.datamaverik.store.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
