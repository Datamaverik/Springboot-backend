package com.datamaverik.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public class NotificationManager {
    private final NotificationService notificationService;

    NotificationManager(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification(String message) {
        if(notificationService == null) {
            System.out.println("notificationService is null");
            return;
        }
        notificationService.sendNotification(message);
    }
}
