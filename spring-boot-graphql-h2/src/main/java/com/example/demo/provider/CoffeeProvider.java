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
import java.util.List;
import java.util.stream.Stream;

@Component
public class CoffeeProvider implements CoffeeDetails {

    private final CoffeeRepository coffeeRepository;

    private final AllCoffeesDataFetcher allCoffeesDataFetcher;

    private final CoffeeDataFetcher coffeeDataFetcher;

    @Value("classpath:coffees.graphql")
    Resource resource;

    private GraphQL graphQL;

    public CoffeeProvider(CoffeeRepository coffeeRepository, AllCoffeesDataFetcher allCoffeesDataFetcher, CoffeeDataFetcher coffeeDataFetcher) {
        this.coffeeRepository = coffeeRepository;
        this.allCoffeesDataFetcher = allCoffeesDataFetcher;
        this.coffeeDataFetcher = coffeeDataFetcher;
    }


    @PostConstruct
    private void loadSchema() throws IOException {

        //Load Books into the Book Repository
        loadDataIntoHSQL();

        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
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
    @Override
    public List<Coffee> findAll() {
        return null;
    }

    @Override
    public Coffee findByCid(String cid) {
        return null;
    }
    */

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allCoffees", allCoffeesDataFetcher)
                        .dataFetcher("coffee", coffeeDataFetcher))
                .build();
    }

    @Override
    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }
}
