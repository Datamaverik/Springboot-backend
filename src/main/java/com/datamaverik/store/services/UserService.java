package com.datamaverik.store.services;

import com.datamaverik.store.entities.Address;
import com.datamaverik.store.entities.User;
import com.datamaverik.store.repositories.AddressRepository;
import com.datamaverik.store.repositories.ProfileRepository;
import com.datamaverik.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
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
}
