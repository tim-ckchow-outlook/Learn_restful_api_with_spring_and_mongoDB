package com.timchow.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    @Autowired
    private MongoTemplate mongoTemplate;

    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public String greeting() {
        try {
            // Execute the ping command on the database
            mongoTemplate.executeCommand("{ ping: 1 }");

            // If the ping command was successful, return a success message
            return "Connected to MongoDB";
        } catch (Exception e) {
            // If the ping command failed, return an error message
            return "Failed to connect to MongoDB: " + e.getMessage();
        }
    }


}
