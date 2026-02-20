package com.example.demo.labs.lab07_transaction;

import jakarta.persistence.*;


@Entity
class TestMember {
    
    @Id @GeneratedValue
    private Long id;

    private String name;

    public TestMember(){

    }

    public TestMember(String name){
        this.name=name;
    }
}
