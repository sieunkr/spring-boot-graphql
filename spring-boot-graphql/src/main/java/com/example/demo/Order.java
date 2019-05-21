package com.example.demo;

import lombok.Data;

@Data
public class Order {

    private String orderId;
    private String description;

    public Order(String orderId, String description){
        this.orderId = orderId;
        this.description = description;
    }

}
