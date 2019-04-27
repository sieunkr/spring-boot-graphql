package com.example.demo.provider;

import com.example.demo.core.Coffee;
import com.example.demo.core.CoffeeDetails;
import com.example.demo.core.CoffeeRepository;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class CoffeeProvider implements CoffeeDetails {

    private final CoffeeRepository coffeeRepository;

    private final AllCoffeesDataFetcher allCoffeesDataFetcher;

    private final AllCoffeesOrderByDataFetcher allCoffeesOrderByDataFetcher;

    private final CoffeeDataFetcher coffeeDataFetcher;

    @Value("classpath:coffees.graphql")
    Resource resource;

    private GraphQL graphQL;

    public CoffeeProvider(CoffeeRepository coffeeRepository, AllCoffeesDataFetcher allCoffeesDataFetcher, AllCoffeesOrderByDataFetcher allCoffeesOrderByDataFetcher, CoffeeDataFetcher coffeeDataFetcher) {
        this.coffeeRepository = coffeeRepository;
        this.allCoffeesDataFetcher = allCoffeesDataFetcher;
        this.allCoffeesOrderByDataFetcher = allCoffeesOrderByDataFetcher;
        this.coffeeDataFetcher = coffeeDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {

        loadDataIntoHSQL(); // 데이터 초기화

        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allCoffees", allCoffeesDataFetcher)
                        .dataFetcher("allCoffeesOrderBy", allCoffeesOrderByDataFetcher)
                        .dataFetcher("coffee", coffeeDataFetcher))
                .build();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {

        Stream.of(
                new Coffee("1", "latte"),
                new Coffee("2", "mocha"),
                new Coffee("3", "americano")
        ).forEach(coffeeRepository::save);
    }

    /*
    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allCoffees", allCoffeesDataFetcher)
                        .dataFetcher("coffee", coffeeDataFetcher))
                .build();
    }
    */
    @Override
    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }




    /*
    @Override
    public List<Coffee> findAll() {
        return null;
    }

    @Override
    public Coffee findByCid(String cid) {
        return null;
    }
    */
}
