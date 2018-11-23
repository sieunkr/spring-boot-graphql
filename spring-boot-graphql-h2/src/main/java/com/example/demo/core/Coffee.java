package com.example.demo.core;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class Coffee {

    @Id
    private String cid;

    private String name;

    public Coffee(String cid, String name) {
        this.cid = cid;
        this.name = name;
    }
}
