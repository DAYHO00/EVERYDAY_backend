package com.example.demo.labs.lab10_id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class IdentityMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected IdentityMember() {}

    public IdentityMember(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}