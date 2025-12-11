package com.datamaverik.store.repositories;

import com.datamaverik.store.dtos.ProfilesDTO;
import com.datamaverik.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @Query(value = "SELECT p.id, u.email FROM profiles p JOIN users u ON p.id = u.id WHERE p.loyalty_points > :minPoints", nativeQuery = true)
    List<ProfilesDTO> findProfilesByLoyaltyPoints(@Param("minPoints") int minPoints);
}
