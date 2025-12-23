package com.datamaverik.store.controllers;

import com.datamaverik.store.dtos.GetOrderDto;
import com.datamaverik.store.exceptions.CartNotFoundException;
import com.datamaverik.store.exceptions.OrderNotFoundException;
import com.datamaverik.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<GetOrderDto>> getOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderDto> getOrderById(
            @PathVariable("orderId") Long orderId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(orderId));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> handleCartNotFoundException(
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("message", "No cart found for this user")
        );
    }
}
