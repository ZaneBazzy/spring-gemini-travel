package com.example.demo;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;

import java.util.Optional;

public interface UserRepository extends DatastoreRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
