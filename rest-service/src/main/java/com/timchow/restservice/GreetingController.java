package com.timchow.restservice;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.timchow.restservice.Entity.GreetingMessage;

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

    @GetMapping("/getGreetingMessage")
    public Greeting getGreetingMessage(){
        try{
            //try to get the greeting message from the collection called "greetingMessage" in the database
            GreetingMessage greetingMessage = mongoTemplate.findById("1", GreetingMessage.class, "greetingMessage");
            //if the greeting message is not null, return the message
            if(greetingMessage != null){
                return new Greeting(greetingMessage.getId(), greetingMessage.getMessage());
            }
            //if the greeting message is null, return an error message
            else{
                return new Greeting("0", "Failed to get greeting message");
            }
            } catch (Exception e) {
            // If the ping command failed, return an error message
            return new Greeting("0", "Failed to connect to MongoDB: " + e.getMessage());
        }
        
    }

    @GetMapping("/setGreetingMessage")
    public String setGreetingMessage(@RequestParam(value = "message", defaultValue = "World") String message){
        try{
            //try to get the greeting message from the collection called "greetingMessage" in the database
            GreetingMessage greetingMessage = mongoTemplate.findById("1", GreetingMessage.class, "greetingMessage");
            //if the greeting message is not null, update the message
            if(greetingMessage != null){
                greetingMessage.setMessage(message);
                mongoTemplate.save(greetingMessage, "greetingMessage");
                return "Successfully updated greeting message";
            }
            //if the greeting message is null, create a new greeting message
            else{
                greetingMessage = new GreetingMessage("1", message);
                mongoTemplate.save(greetingMessage, "greetingMessage");
                return "Successfully created greeting message";
            }
            } catch (Exception e) {
            // If the ping command failed, return an error message
            return "Failed to connect to MongoDB: " + e.getMessage();
        }
        
    }

    @GetMapping("/getDocument/{id}")
    public Map<String, Object> getDocument(@PathVariable String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Map.class, "collectionName");
    }


}
