package com.example.demo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CoffeeDataFetcher implements DataFetcher<Coffee> {

    private final CoffeeRepository coffeeRepository;

    public CoffeeDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }


    @Override
    public Coffee get(DataFetchingEnvironment environment) {
        String name = environment.getArgument("name");
        return coffeeRepository.findByName(name);
    }


}
