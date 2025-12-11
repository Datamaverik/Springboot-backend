package com.datamaverik.store.repositories;

import com.datamaverik.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
