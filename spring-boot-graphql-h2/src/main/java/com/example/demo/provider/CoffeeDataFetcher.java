package com.example.demo.provider;

import com.example.demo.core.Coffee;
import com.example.demo.core.CoffeeRepository;
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
    public Coffee get(DataFetchingEnvironment dataFetchingEnvironment) {
        String cid = dataFetchingEnvironment.getArgument("id");
        return  coffeeRepository.findByCid(cid);
    }
}
