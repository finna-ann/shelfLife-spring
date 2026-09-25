package com.spring.shelfLife.controller;

import com.spring.shelfLife.service.GreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greet {

    @Autowired
    GreetService greetService;

    @GetMapping("/hello")
    public String greet(){
       return greetService.greet();
    }
}
