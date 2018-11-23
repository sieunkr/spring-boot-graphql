package com.example.demo.provider;

import com.example.demo.core.Coffee;
import com.example.demo.core.CoffeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllCoffeesDataFetcher implements DataFetcher<List<Coffee>> {

    private final CoffeeRepository coffeeRepository;

    public AllCoffeesDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public List<Coffee> get(DataFetchingEnvironment environment) {
        return coffeeRepository.findAll();
    }
}
