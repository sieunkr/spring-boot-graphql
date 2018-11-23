package com.example.demo.core;

import graphql.ExecutionResult;

import java.util.List;

public interface CoffeeDetails {
    /*
    List<Coffee> findAll();
    Coffee findByCid(String cid);
    */
    ExecutionResult execute(String query);
}
