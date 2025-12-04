package com.datamaverik.store;

public class InMemoryUserRepository implements UserRepository {

    private static User[] users;
    private static int ind = 0;

    InMemoryUserRepository(int capacity) {
        users = new User[capacity];
    }

    @Override
    public void save(User user) {
        if(ind >= users.length) {
            System.out.println("Memory is full");
            return;
        }
        users[ind++] = user;
        System.out.println("User saved in memory");
        System.out.println(user);
    }

    @Override
    public User findByEmail(String email) {
        for(User user : users) {
            if(user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
