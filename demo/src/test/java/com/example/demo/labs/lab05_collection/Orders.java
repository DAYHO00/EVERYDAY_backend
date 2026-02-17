package com.example.demo.labs.lab05_collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Orders {
    private final List<Order> orders;

    public Orders() {
        this.orders = new ArrayList<>();
    }

    public void add(Order order){
        if(order==null){
            throw new IllegalArgumentException("Order cannot be null");
        }
        orders.add(order);
    }

    public List<Order> getAll(){
        return Collections.unmodifiableList(orders);
    }

    public double getTotalAmount(){
        double sum=0.0;
        for(int i=0;i<orders.size();i++){
            sum+=orders.get(i).getAmount();
        }
        return sum;
    }
}
