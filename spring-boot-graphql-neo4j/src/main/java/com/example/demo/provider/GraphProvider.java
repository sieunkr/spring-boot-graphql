package com.example.demo.provider;

import com.example.demo.core.GraphDetails;
import graphql.ExecutionResult;
import org.springframework.stereotype.Component;

@Component
public class GraphProvider implements GraphDetails {

    @Override
    public ExecutionResult execute(String query) {
        return null;
    }
}
