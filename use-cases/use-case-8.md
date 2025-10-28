# USE CASE: 8 Update an Employee’s Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *HR Advisor* I want *to delete an employee's details so that the company is compliant with data retention legislation.*

### Scope

Company

### Level

Primary task.

### Preconditions

Employee exists in the database.

### Success End Condition

Employee details are deleted from the database.

### Failed End Condition

Employee details remain in the system.

### Primary Actor

HR Advisor

### Trigger

Data retention period expires or a deletion request is raised.

## MAIN SUCCESS SCENARIO

1. HR advisor selects employee record for deletion.
2. System prompts for confirmation.
3. HR advisor confirms deletion.
4. System removes employee details from the database.

## EXTENSIONS

Employee not found: HR advisor is notified.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
