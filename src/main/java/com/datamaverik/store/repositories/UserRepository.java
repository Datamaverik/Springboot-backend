package com.datamaverik.store.repositories;

import com.datamaverik.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
