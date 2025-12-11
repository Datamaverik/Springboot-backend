package com.datamaverik.store;

import com.datamaverik.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {
    //  entry point of the application like index.js
	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(StoreApplication.class, args);
        var service = context.getBean(UserService.class);
        service.fetchProductsByCriteria();
	}
}
