package com.example.demo.labs.lab10_id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(
        name = "seq_member_gen",
        sequenceName = "seq_member_seq",
        allocationSize = 1
)
public class SequenceMember {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member_gen")
    private Long id;

    private String name;

    protected SequenceMember() {}

    public SequenceMember(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}