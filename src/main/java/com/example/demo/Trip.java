package com.example.demo;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "trips")
public class Trip {

    @Id
    private Long id;

    private String destination;
    private String notes;
    private Long userId;

    public Trip() {
    }

    public Trip(String destination, String notes, Long userId) {
        this.destination = destination;
        this.notes = notes;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
