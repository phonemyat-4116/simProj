import com.napier.sem.App;
import com.napier.sem.Department;
import com.napier.sem.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    static App app;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @BeforeAll
    static void init() {
        app = new App();
    }

    @Test
    void printSalariesTestNull() {
        app.printSalaries(null);
    }

    @Test
    void printSalariesTestEmpty() {
        ArrayList<Employee> employees = new ArrayList<>();
        app.printSalaries(employees);
    }

    @Test
    void printSalariesTestContainsNull() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(null);
        app.printSalaries(employees);
    }

    @Test
    void printSalaries(){
        // Create manager employee
        Employee manager = new Employee();
        manager.emp_no = 1001;
        manager.first_name = "John";
        manager.last_name = "Smith";

        // Create department and assign manager
        Department dept = new Department();
        dept.dept_no = "d001";
        dept.dept_name = "Engineering";
        dept.manager = manager;

        // Create employee and assign department and manager
        Employee emp = new Employee();
        emp.emp_no = 1002;
        emp.first_name = "Alice";
        emp.last_name = "Schiwmmer";
        emp.salary = 75000;
        emp.dept = dept;
        emp.manager = manager;

        // Put employee in list
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(emp);

        // Run the method
        app.printSalaries(employees);
    }

    /**=============================== */
    // Test for display

    @Test
    void displayEmployeeTestNull(){
        app.displayEmployee(null);
    }

    @Test
    void displayEmployeTestEmpty(){
        Employee emp = new Employee();
        app.displayEmployee(emp);
    }

    @Test
    void displayEmployee(){
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";
        emp.title = "Engineer";
        emp.salary = 55000;

        app.displayEmployee(emp);

    }

}
// Create path for add