package com.napier.sem;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class App {
    public static void main(String[] args)
    {
        MongoDatabase database;
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            database = mongoClient.getDatabase("db");

            MongoCollection<Document> collection = database.getCollection("test");

            Document doc = new Document("name", "Phone Myat")
                    .append("class", "Software Engineering")
                    .append("year", "2025")
                    .append("result", new Document("CW", 95).append("EX", 85));
            collection.insertOne(doc);

            Document myDoc = collection.find().first();
            System.out.println(myDoc.toJson());

        }

    }
}

/*
✅ Key points:

Runner’s JDK 20 is only used for compiling outside Docker.

Docker JDK 21 is only used for running the code inside the container.

No error occurs because compiled Java 20 classes are compatible with JDK 21.
 */
