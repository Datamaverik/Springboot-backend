package com.datamaverik.store;

public class UserService {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void registerUser(User user) {
        userRepository.save(user);
        notificationService.send("User registered successfully", user.getEmail());
    }
}
