package com.example.demo.provider;

import com.example.demo.core.Person;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class PersonDataFetcher implements DataFetcher<Person> {



    @Override
    public Person get(DataFetchingEnvironment dataFetchingEnvironment) {
        String cid = dataFetchingEnvironment.getArgument("name");

        return null;
    }
}
