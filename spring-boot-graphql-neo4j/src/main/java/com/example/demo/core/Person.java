package com.example.demo.core;

import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Getter
public class Person {

    private String id;
    private String name;
    private String title;

    public Person(){
    }

    public Person(String id, String name, String title){
        this.id = id;
        this.name = name;
        this.title = title;
    }

}
