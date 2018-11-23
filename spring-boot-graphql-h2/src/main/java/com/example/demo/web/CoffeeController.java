package com.example.demo.web;

import com.example.demo.core.CoffeeUseCase;
import graphql.ExecutionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeUseCase coffeeUseCase;

    public CoffeeController(CoffeeUseCase coffeeUseCase) {
        this.coffeeUseCase = coffeeUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> getAllCoffees(@RequestBody String query) {
        ExecutionResult execute = coffeeUseCase.execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
