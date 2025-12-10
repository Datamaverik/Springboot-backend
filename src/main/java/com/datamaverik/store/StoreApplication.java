package com.datamaverik.store;

import com.datamaverik.store.entities.Address;
import com.datamaverik.store.entities.Profile;
import com.datamaverik.store.entities.Tag;
import com.datamaverik.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {
    //  entry point of the application like index.js
	public static void main(String[] args) {
//		ApplicationContext context =  SpringApplication.run(StoreApplication.class, args);
        var user = User.builder()
                .name("John")
                .password("pass")
                .email("email")
                .build();

        var profile = Profile.builder()
                .bio("bio")
                .build();

        user.setProfile(profile);
        profile.setUser(user);

        System.out.println(user);
	}
}
