package com.example.demo.provider;

import com.example.demo.core.GraphDetails;
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

@Component
public class GraphProvider implements GraphDetails {

    private final PersonDataFetcher personDataFetcher;

    @Value("classpath:person.graphql")
    Resource resource;

    private GraphQL graphQL;

    public GraphProvider(PersonDataFetcher personDataFetcher) {
        this.personDataFetcher = personDataFetcher;
    }

    @Override
    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }

    @PostConstruct
    private void loadSchema() throws IOException {


        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("person", personDataFetcher)
                )
                .build();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }
}
