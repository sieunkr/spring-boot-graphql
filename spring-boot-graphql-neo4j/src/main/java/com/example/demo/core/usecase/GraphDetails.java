package com.example.demo.core.usecase;

import graphql.ExecutionResult;

public interface GraphDetails {
    ExecutionResult execute(String query);
}
