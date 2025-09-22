/*
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
        try (MongoClient mongoClient = MongoClients.create("mongodb://mongodb-server:27017")) {
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

 */

package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        Connection con = null;
        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                // Wait a bit
                Thread.sleep(10000);
                // Exit for loop
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
}

/*
✅ Key points:

Runner’s JDK 20 is only used for compiling outside Docker.

Docker JDK 21 is only used for running the code inside the container.

No error occurs because compiled Java 20 classes are compatible with JDK 21.
 */
