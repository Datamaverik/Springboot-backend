package com.datamaverik.store.services;

import com.datamaverik.store.entities.Address;
import com.datamaverik.store.entities.User;
import com.datamaverik.store.repositories.AddressRepository;
import com.datamaverik.store.repositories.ProductRepository;
import com.datamaverik.store.repositories.ProfileRepository;
import com.datamaverik.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(1L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void retrieveAddress() {
        var address = addressRepository.findById(1).orElseThrow();
        System.out.println(address.getStreet() + ", " + address.getCity() + ", " + address.getState() + ", " + address.getZip());
    }

    public void persistRelated() {
        var user = User.builder()
                .name("Joginder")
                .email("joginder@gmail.com")
                .password("pass")
                .build();

        var address = Address.builder()
                .street("jamal path")
                .city("mirzapur")
                .state("up")
                .zip("212312")
                .build();

        user.addAddress(address);

        userRepository.save(user);
    }

    public void deleteRelated() {
        userRepository.deleteById(1L);
    }

    @Transactional
    public void addProductsToWishlist() {
        var user = userRepository.findById(2L).orElseThrow();
        productRepository.findAll().forEach(user::addProductToWishlist);
        userRepository.save(user);
    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte)1);
    }

    @Transactional
    public void fetchProducts() {
        var products = productRepository.findProducts(BigDecimal.valueOf(1), BigDecimal.valueOf(10000));
        products.forEach(product -> {
            System.out.println(product.getName() + " " + product.getDescription());
        });
    }

    @Transactional
    public void fetchUser() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void findProfiles() {
        var users = userRepository.findLoyalUsers(2);
        users.forEach(p -> {
            System.out.println(p.getId() + " " + p.getEmail());
        });
    }

    @Transactional
    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria(null, BigDecimal.valueOf(1), BigDecimal.valueOf(10000));
        products.forEach(System.out::println);
    }
}
