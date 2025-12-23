package com.datamaverik.store.controllers;

import com.datamaverik.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrders() {
        var orderDtos = orderService.getAllOrders();

        return ResponseEntity.status(HttpStatus.OK).body(orderDtos);
    }
}
