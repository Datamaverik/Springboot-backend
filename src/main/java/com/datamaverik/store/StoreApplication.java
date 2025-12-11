package com.datamaverik.store;

import com.datamaverik.store.repositories.UserRepository;
import com.datamaverik.store.services.CategoryService;
import com.datamaverik.store.services.ProductService;
import com.datamaverik.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class StoreApplication {
    //  entry point of the application like index.js
	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(StoreApplication.class, args);
        var repository = context.getBean(UserRepository.class);
        var service = context.getBean(ProductService.class);

        service.removeProduct();
	}
}
