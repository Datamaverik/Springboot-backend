package com.datamaverik.store;

public class EmailNotificationService implements NotificationService{

    private final int port;

    EmailNotificationService(int port) {
        this.port = port;
    }

    @Override
    public void send(String message, String recipientMail) {
        System.out.println("Sending email to " + recipientMail + " at port " + port);
        System.out.println("message: " + message);
    }
}
