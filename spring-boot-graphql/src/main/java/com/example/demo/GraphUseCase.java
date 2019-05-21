package com.example.demo;

import graphql.ExecutionResult;

public interface GraphUseCase {
    ExecutionResult execute(String query);
}
