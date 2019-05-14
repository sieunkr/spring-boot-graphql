package com.example.demo.core;

import graphql.ExecutionResult;

public interface GraphDetails {
    ExecutionResult execute(String query);
}
