package com.timchow.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);

		// ServerApi serverApi = ServerApi.builder()
        //         .version(ServerApiVersion.V1)
        //         .build();
        // MongoClientSettings settings = MongoClientSettings.builder()
        //         .applyConnectionString(new ConnectionString(connectionString))
        //         .serverApi(serverApi)
        //         .build();
        // // Create a new client and connect to the server
        // try (MongoClient mongoClient = MongoClients.create(settings)) {
        //     try {
        //         // Send a ping to confirm a successful connection
        //         MongoDatabase database = mongoClient.getDatabase("admin");
        //         database.runCommand(new Document("ping", 1));
        //         System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
        //     } catch (MongoException e) {
        //         e.printStackTrace();
        //     }
        // }
	}

}
