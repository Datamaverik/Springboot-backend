package com.datamaverik.store;

import org.springframework.stereotype.Service;

public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("PayPalPaymentService");
        System.out.println("Amout: " + amount);
    }
}
