package com.example.demo;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import java.util.List;

public interface TripRepository extends DatastoreRepository<Trip, Long> {
    List<Trip> findByUserId(Long userId);
}
