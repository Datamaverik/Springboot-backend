package com.datamaverik.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${payment-gateway:stripe}")
    private String paymentGateway;

    @Value("${repository-capacity:50}")
    private int capacity;

    @Value("${port:4000}")
    private int port;

    @Bean
    public PaymentService stripe() {
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal() {
        return new PayPalPaymentService();
    }

    @Bean
    public OrderService orderService() {
        if(paymentGateway.equals("stripe"))
            return new OrderService(stripe());
        return new OrderService(paypal());
    }

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository(capacity);
    }

    public NotificationService notificationService() {
        return new EmailNotificationService(port);
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository(), notificationService());
    }
}
