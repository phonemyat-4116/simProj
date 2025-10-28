# USE CASE: 3 Produce a Report on the Salary of Employees in My Department

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *department manager* I want *to produce a report on the salary of employees in my department so that I can support financial reporting for my department.*

### Scope

Department

### Level

Primary task.

### Preconditions

Database contains current employee salary data.

### Success End Condition

A salary report for the department is available for HR to provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

Department Manager

### Trigger

A request for departmental finance information is made.

## MAIN SUCCESS SCENARIO

1. Department manager requests salary report for their department.
2. System verifies manager’s department.
3. System retrieves salary data for employees in the department.
4. Department manager receives report.

## EXTENSIONS

No employees in department: Report shows empty results.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
