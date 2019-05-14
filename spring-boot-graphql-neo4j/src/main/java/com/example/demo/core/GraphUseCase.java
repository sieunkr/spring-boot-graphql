package com.example.demo.core;

import graphql.ExecutionResult;
import org.springframework.stereotype.Service;

@Service
public class GraphUseCase {

    private final GraphDetails graphDetails;

    public GraphUseCase(GraphDetails graphDetails) {
        this.graphDetails = graphDetails;
    }

    public ExecutionResult execute(String query){
        return graphDetails.execute(query);
    }
}
