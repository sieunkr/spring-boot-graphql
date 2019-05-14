package com.example.demo.provider;

import com.example.demo.core.Person;
import com.example.demo.core.PersonRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class PersonDataFetcher implements DataFetcher<Person> {

    private final PersonRepository personRepository;

    public PersonDataFetcher(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person get(DataFetchingEnvironment dataFetchingEnvironment) {
        String name = dataFetchingEnvironment.getArgument("name");

        return personRepository.findByName(name);

    }
}
