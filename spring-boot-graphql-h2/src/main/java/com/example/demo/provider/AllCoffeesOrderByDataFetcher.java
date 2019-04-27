package com.example.demo.provider;

import com.example.demo.core.Coffee;
import com.example.demo.core.CoffeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllCoffeesOrderByDataFetcher implements DataFetcher<List<Coffee>> {

    private final CoffeeRepository coffeeRepository;

    public AllCoffeesOrderByDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public List<Coffee> get(DataFetchingEnvironment environment) {
        //TODO:리팩토링 필요
        return coffeeRepository.findAll(new Sort(Sort.Direction.fromString(environment.getArgument("sortOrder")), ((String)environment.getArgument("sortBy"))));
    }
}
