package com.example.demo.labs.lab05_collection;

public class Order {
    private final long id;
    private final double amount;

    public Order(long id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }
}
