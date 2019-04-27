package com.example.demo.core;

import graphql.ExecutionResult;
//import java.util.List;

public interface CoffeeDetails {

    ExecutionResult execute(String query);

    /*
    List<Coffee> findAll();
    Coffee findByCid(String cid);
    */
}



