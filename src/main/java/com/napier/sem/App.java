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
import java.util.ArrayList;

public class App {

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public Employee getEmployee(int ID) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT e.emp_no, e.first_name, e.last_name, " +
                            "t.title, s.salary, d.dept_name, d.dept_no, " +
                            "mgr.emp_no as mgr_no, mgr.first_name as mgr_first, mgr.last_name as mgr_last " +
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
            if (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset.getString("title");
                emp.salary = rset.getInt("salary");

                Department dept = new Department();
                dept.dept_name = rset.getString("dept_name");
                dept.dept_no = rset.getString("dept_no");
                emp.dept = dept; // assign department to employee

                // Add department manager
                int mgrNo = rset.getInt("mgr_no");
                if(mgrNo != 0){
                    Employee manager = new Employee();
                    manager.emp_no = mgrNo;
                    manager.first_name = rset.getString("mgr_first");
                    manager.last_name = rset.getString("mgr_last");
                    dept.manager = manager; // assign manager to department
                    emp.manager = manager; // also assign manager to employee
                }

                return emp;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public Employee getEmployeeByName(String first_name, String last_name) {
        // Create string for SQL statement
        String sql =
                "SELECT e.emp_no, e.first_name, e.last_name, " +
                        "t.title, s.salary, d.dept_name, d.dept_no, " +
                        "mgr.emp_no as mgr_no, mgr.first_name as mgr_first, mgr.last_name as mgr_last " +
                        "FROM employees e " +
                        "LEFT JOIN titles t ON e.emp_no = t.emp_no AND t.to_date = '9999-01-01' " +
                        "LEFT JOIN salaries s ON e.emp_no = s.emp_no AND s.to_date = '9999-01-01' " +
                        "LEFT JOIN dept_emp de ON e.emp_no = de.emp_no AND de.to_date = '9999-01-01' " +
                        "LEFT JOIN departments d ON de.dept_no = d.dept_no " +
                        "LEFT JOIN dept_manager dm ON de.dept_no = dm.dept_no AND dm.to_date = '9999-01-01' " +
                        "LEFT JOIN employees mgr ON dm.emp_no = mgr.emp_no " +
                        "WHERE e.first_name = ? AND  e.last_name = ?";

        try(PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);

            // Execute SQL statement
            try(ResultSet rset = pstmt.executeQuery()) {
                // Return new employee if valid.
                // Check one is returned
                if (rset.next()) {
                    Employee emp = new Employee();
                    emp.emp_no = rset.getInt("emp_no");
                    emp.first_name = rset.getString("first_name");
                    emp.last_name = rset.getString("last_name");
                    emp.title = rset.getString("title");
                    emp.salary = rset.getInt("salary");

                    Department dept = new Department();
                    dept.dept_name = rset.getString("dept_name");
                    dept.dept_no = rset.getString("dept_no");
                    emp.dept = dept; // assign department to employee

                    // Add department manager
                    int mgrNo = rset.getInt("mgr_no");
                    if(mgrNo != 0){
                        Employee manager = new Employee();
                        manager.emp_no = mgrNo;
                        manager.first_name = rset.getString("mgr_first");
                        manager.last_name = rset.getString("mgr_last");
                        dept.manager = manager; // assign manager to department
                        emp.manager = manager; // also assign manager to employee
                    }

                    return emp;
                } else
                    return null;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displayEmployee(Employee emp) {
        if (emp != null) {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + (emp.dept != null ? emp.dept.dept_name : "No Department") + "\n"
                            + "Manager: " + (emp.manager != null ? emp.manager.first_name + " " + emp.manager.last_name : "No Manager") + "\n");
        } else {
            System.out.println("Employee not found!");
        }
    }

    /**
     * Gets all the current employees and salaries.
     *
     * @return A list of all employees and salaries, or null if there is an error.
     */
    public ArrayList<Employee> getAllSalaries() {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create String for SQL statement
            String strSelect =
                    "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                            + "FROM employees, salaries "
                            + "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' "
                            + "ORDER BY employees.emp_no ASC";
            // Execute SQL Statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                employees.add(emp);
            }
            return employees;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }

    }

    public void printSalaries(ArrayList<Employee> employees) {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s %-8s", "Emp No", "First Name", "Last Name", "Salary", "Department No"));

        for (Employee emp : employees) {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name, emp.salary, emp.dept.dept_no);
            System.out.println(emp_string);
        }
    }


    public Department getDepartment(String dept_no) {
        String sql = "SELECT d.dept_no, d.dept_name, " +
                "e.emp_no, e.first_name, e.last_name " +
                "FROM departments d " +
                "LEFT JOIN dept_manager dm ON d.dept_no = dm.dept_no " +
                "LEFT JOIN employees e ON dm.emp_no = e.emp_no " +
                "WHERE d.dept_no = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dept_no);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Department department = new Department();
                    department.dept_no = rs.getString("dept_no");
                    department.dept_name = rs.getString("dept_name");

                    int mangerEmployee = rs.getInt("emp_no");
                    if(mangerEmployee != 0){
                        Employee manager = new Employee();
                        manager.first_name = rs.getString("first_name");
                        manager.last_name = rs.getString("last_name");
                        department.manager = manager;
                    }

                    return department;
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get the department: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Employee> getSalariesByDepartment(Department dept) {
        String sql = "SELECT e.emp_no, e.first_name, e.last_name, s.salary " +
                "FROM employees e " +
                "JOIN salaries s ON e.emp_no = s.emp_no " +
                "JOIN dept_emp de ON e.emp_no = de.emp_no " +
                "JOIN departments d ON de.dept_no = d.dept_no " +
                "WHERE s.to_date = '9999-01-01' " +
                "  AND d.dept_no = ? " +
                "ORDER BY e.emp_no ASC";

        ArrayList<Employee> employees = new ArrayList<>();


        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dept.dept_no);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Employee emp = new Employee();
                    emp.emp_no = rs.getInt("emp_no");
                    emp.first_name = rs.getString("first_name");
                    emp.last_name = rs.getString("last_name");
                    emp.salary = rs.getInt("salary");
                    emp.dept = dept;
                    employees.add(emp);

                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get salary by department: " + e.getMessage());
        }

        return employees;
    }

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Get Employee
//        Employee emp = a.getEmployee(255530);

        /*
        // Check if employee was found
        if (emp == null) {
            System.out.println("Employee " + emp.emp_no + " is not found in database!");
        } else {
            System.out.println("Employee found: " + emp.emp_no);
        }

        // Display results
        a.displayEmployee(emp);
         */
//        ArrayList<Employee> employees = a.getAllSalaries();

        // Test the size of the returned data - should be 240124
//        System.out.println(employees.size());

//        a.printSalaries(employees);

        Department dept = a.getDepartment("d002");

        ArrayList<Employee> salariesByDepartment = a.getSalariesByDepartment(dept);
        a.printSalaries(salariesByDepartment);

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
