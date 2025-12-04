package com.datamaverik.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(@Qualifier("paypal") PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    public void placeOrder() {
        if(paymentService == null) {
            System.out.println("paymentService is null");
            return;
        }
        paymentService.processPayment(10);
    }
}
