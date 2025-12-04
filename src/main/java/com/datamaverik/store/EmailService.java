package com.datamaverik.store;

import org.springframework.stereotype.Service;

@Service("email")
public class EmailService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email");
        System.out.println("message: " + message);
    }
}
