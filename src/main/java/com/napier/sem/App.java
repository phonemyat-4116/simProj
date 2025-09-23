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

public class App {

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
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

        int retries = 10;
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
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
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

    public Employee getEmployee(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT e.emp_no, e.first_name, e.last_name, " +
                            "t.title, s.salary, d.dept_name, " +
                            "CONCAT(mgr.first_name, ' ', mgr.last_name) as manager " +
                            "FROM employees e " +
                            "LEFT JOIN titles t ON e.emp_no = t.emp_no AND t.to_date = '9999-01-01' " +
                            "LEFT JOIN salaries s ON e.emp_no = s.emp_no AND s.to_date = '9999-01-01' " +
                            "LEFT JOIN dept_emp de ON e.emp_no = de.emp_no AND de.to_date = '9999-01-01' " +
                            "LEFT JOIN departments d ON de.dept_no = d.dept_no " +
                            "LEFT JOIN dept_manager dm ON de.dept_no = dm.dept_no AND dm.to_date = '9999-01-01' " +
                            "LEFT JOIN employees mgr ON dm.emp_no = mgr.emp_no " +
                            "WHERE e.emp_no = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset.getString("title");
                emp.salary = rset.getInt("salary");
                emp.dept_name = rset.getString("dept_name");
                emp.manager = rset.getString("manager");
                return emp;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displayEmployee(Employee emp){
        if(emp != null)
        {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");
        }
        else
        {
            System.out.println("Employee not found!");
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Get Employee
        Employee emp = a.getEmployee(255530);

        // Check if employee was found
        if (emp == null) {
            System.out.println("Employee " + emp.emp_no + " is not found in database!");
        } else {
            System.out.println("Employee found: " + emp.emp_no);
        }

        // Display results
        a.displayEmployee(emp);

        // Disconnect from database
        a.disconnect();

        System.out.println("Application finished.");
    }
}

/*
✅ Key points:

Runner’s JDK 20 is only used for compiling outside Docker.

Docker JDK 21 is only used for running the code inside the container.

No error occurs because compiled Java 20 classes are compatible with JDK 21.
 */
