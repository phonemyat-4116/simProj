package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee(255530);
        assertEquals(emp.emp_no, 255530);
        assertEquals(emp.first_name, "Ronghao");
        assertEquals(emp.last_name, "Garigliano");
    }

    @Test
    void testGetEmployeeByName()
    {
        Employee emp = app.getEmployeeByName("Parto", "Bamford");
        assertEquals(emp.emp_no, 10003);
        assertEquals(emp.first_name, "Parto");
        assertEquals(emp.last_name, "Bamford");

    }

    @Test
    void testDisplayEmployee()
    {
        Employee emp = app.getEmployee(255530);
        app.displayEmployee(emp);
    }

    @Test
    void testGetAllSalaries()
    {
        ArrayList<Employee> salaries = app.getAllSalaries();

        // Check if salaries is not null
        assertNotNull(salaries, "Employee salary list should not be null");

        // Check if a list is not empty
        assertFalse(salaries.isEmpty(), "Employee salary list should not be empty");

        // Check first record data consistency
        Employee firstEmp = salaries.get(0);
        assertNotNull(firstEmp.emp_no, "Employee number should not be null");
        assertNotNull(firstEmp.first_name, "First name should not be null");
        assertNotNull(firstEmp.last_name, "Last name should not be null");

    }

    @Test
    void testGetDepartment()
    {
        Department dept = app.getDepartment("d002");

        assertNotNull(dept, "Department should not be null");
        assertEquals("d002", dept.dept_no);
        assertEquals("Finance", dept.dept_name);

        // Check Manager Detail
        assertNotNull(dept.manager);
        assertNotNull(dept.manager.first_name);
        assertNotNull(dept.manager.last_name);

    }

    @Test
    void testGetSalariesByDepartment()
    {
        Department dept = app.getDepartment("d002");

        assertNotNull(dept, "Department should not be null");
        assertEquals("d002", dept.dept_no);

        ArrayList<Employee> salaries = app.getSalariesByDepartment(dept);

        assertNotNull(salaries, "Employee salary list should not be null");
        assertFalse(salaries.isEmpty(), "Employee salary list should not be empty");

        for(Employee emp : salaries) {
            assertNotNull(emp.dept);
            assertEquals(dept.dept_no, emp.dept.dept_no,
                    "Employee's department number should match the given department");
        }

    }

    @Test
    void testPrintSalaries()
    {
        Department dept = app.getDepartment("d002");

        ArrayList<Employee> salariesByDepartment = app.getSalariesByDepartment(dept);
        app.printSalaries(salariesByDepartment);
    }




}