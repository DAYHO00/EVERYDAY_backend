package com.example.demo.labs.lab05_collection;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OrdersTest {
    
    @Test
    void add_null_order_should_throw(){

        Orders orders=new Orders();

        IllegalArgumentException e =assertThrows(
            IllegalArgumentException.class,
            ()->orders.add(null)
        );

        assertTrue(e.getMessage().contains("null"));
    }
    @Test
    void getAll_should_return_unmodifiable_List(){
        Orders orders = new Orders();

        orders.add(new Order(1L,1000.0));

        List<Order> list = orders.getAll();

        assertThrows(UnsupportedOperationException.class, ()->{
            list.add(new Order(2L,2000.0));
        });
    }

    @Test
    void getTotalAmount_should_sum_amounts(){
        Orders orders= new Orders();
        orders.add(new Order(1L,1000.0));
        orders.add(new Order(2L,2500.0));

        assertEquals(3500.0,orders.getTotalAmount());    }
}
