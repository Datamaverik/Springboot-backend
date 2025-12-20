package com.datamaverik.store.services;

import com.datamaverik.store.controllers.OrderRepository;
import com.datamaverik.store.dtos.OrderDto;
import com.datamaverik.store.entities.Order;
import com.datamaverik.store.entities.OrderItem;
import com.datamaverik.store.entities.OrderStatus;
import com.datamaverik.store.exceptions.CartNotFoundException;
import com.datamaverik.store.repositories.CartRepository;
import com.datamaverik.store.repositories.UserRepository;
import io.jsonwebtoken.impl.security.EdwardsCurve;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CheckoutService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;

    public OrderDto createOrder(UUID cartId) {
        var user = authService.getCurrentUser();
        if(user == null)
            throw new UsernameNotFoundException("User not found");

        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null || cart.getItems().isEmpty())
            throw new CartNotFoundException();

        var customer = userRepository.findById(user.getId()).orElseThrow();

        var order = Order.builder()
                .status(OrderStatus.PENDING)
                .customer(customer)
                .totalPrice(cart.getTotalPrice())
                .build();

        var orderItems = new LinkedHashSet<OrderItem>();

        cart.getItems().forEach(cartItem -> {
            var product = cartItem.getProduct();

            var orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setUnitPrice(product.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            orderItems.add(orderItem);
        });
        order.setItems(orderItems);

        orderRepository.save(order);
        //  delete the cart when the payment is confirmed

        var orderDto = new OrderDto();
        orderDto.setOrderId(order.getId());

        return orderDto;
    }
}
