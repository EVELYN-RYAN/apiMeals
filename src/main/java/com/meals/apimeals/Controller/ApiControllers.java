package com.meals.apimeals.Controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class ApiControllers {

    @GetMapping(value = "/")
    public String getPage() {
        return "Hello World";
    }

}
