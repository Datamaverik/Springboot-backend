package com.datamaverik.store;

import com.datamaverik.store.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

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

        var product = Product.builder()
                .name("PS5")
                .description("description")
                .price(BigDecimal.valueOf(65000.00))
                .build();

        user.addProductToWishlist(product);

        System.out.println(user);
	}
}
