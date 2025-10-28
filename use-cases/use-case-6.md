# USE CASE: 6 View an Employee’s Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *HR Advisor* I want *to view an employee's details so that the employee's promotion request can be supported.*

### Scope

Company

### Level

Primary task.

### Preconditions

Employee exists in the database.

### Success End Condition

Employee details are displayed.

### Failed End Condition

No details are shown.

### Primary Actor

HR Advisor

### Trigger

Promotion request is submitted.

## MAIN SUCCESS SCENARIO

1. HR advisor searches for employee in the system.
2. System retrieves and displays employee details.
3. System validates and saves details.
4. New employee is added to a payroll system.

## EXTENSIONS

Employee does not exist: HR advisor is notified no record found.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
