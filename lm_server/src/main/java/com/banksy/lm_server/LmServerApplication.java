package com.banksy.lm_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmServerApplication.class, args);
    }

    @GetMapping
    public String health(){
        return "SUCCESS";
    }
}
