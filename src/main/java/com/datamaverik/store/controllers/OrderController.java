package com.datamaverik.store.controllers;

import com.datamaverik.store.dtos.GetOrderDto;
import com.datamaverik.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<GetOrderDto>> getOrders() {
        var orders = orderService.getAllOrders();

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderDto> getOrderById(
            @PathVariable Long orderId
    ) {
        var order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
