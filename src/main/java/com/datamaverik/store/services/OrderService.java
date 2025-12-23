package com.datamaverik.store.services;

import com.datamaverik.store.dtos.GetOrderDto;
import com.datamaverik.store.mappers.OrderMapper;
import com.datamaverik.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<GetOrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        if(user == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        var orders = orderRepository.getAllByCustomer(user).orElse(null);
        if(orders == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return orders.stream().map(orderMapper::toDto).toList();
    }
}
