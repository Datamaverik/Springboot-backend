package com.datamaverik.store;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("Order service created");
    }

    @PostConstruct
    public void init() {
        System.out.println("Order service post construct");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Order service cleanup");
    }

    public void placeOrder() {
        if(paymentService == null) {
            System.out.println("paymentService is null");
            return;
        }
        paymentService.processPayment(10);
    }
}
