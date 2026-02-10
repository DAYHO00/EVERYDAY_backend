package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id //primary key
    @GeneratedValue
    private Long id;
    
    private String name;
    private Integer age;
    protected Member() {
    }

    public Long getId() {
        return id;
    }

    public Member(String name) {
        this.name = name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Member{id=" + id + ", name='" + name + "', age=" + age + "}";
    }
}
