package com.datamaverik.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

public class StripePaymentService implements PaymentService {
    @Value("${stripe.apiUrl:https://api.default.com}")
    private String apiUrl;

    @Value("${stripe.timeout:3000}")
    private int timeout;

    @Value("${stripe.supported-currencies:USD,RUP}")
    private List<String> currencies;

    @Override
    public void processPayment(double amount) {
        System.out.println(apiUrl);
        System.out.println(timeout);
        System.out.println(currencies);
        System.out.println("STRIPE");
        System.out.println("Amount: " + amount);
    }
}
