# Use the latest MySQL image
FROM mysql:8.0
# Set the working directory
WORKDIR /tmp
# Copy all the files to the working directory of the container
COPY test_db/*.sql /tmp/
COPY test_db/*.dump /tmp/
# Copy the main SQL file to docker-entrypoint-initdb.d.
# Scripts and SQL files in this folder are executed on container startup.
# This is specific to MySQL.
#COPY test_db/employees.sql /docker-entrypoint-initdb.d/01_employees_schema.sql
#COPY test_db/load_departments.sql /docker-entrypoint-initdb.d/02_load_departments.sql
#COPY test_db/load_employees.dump /docker-entrypoint-initdb.d/03_load_employees.sql
#COPY test_db/load_dept_emp.dump /docker-entrypoint-initdb.d/04_load_dept_emp.sql
#COPY test_db/load_dept_manager.dump /docker-entrypoint-initdb.d/05_load_dept_manager.sql
#COPY test_db/load_titles.dump /docker-entrypoint-initdb.d/06_load_titles.sql
#COPY test_db/load_salaries1.dump /docker-entrypoint-initdb.d/07_load_salaries1.sql
#COPY test_db/load_salaries2.dump /docker-entrypoint-initdb.d/08_load_salaries2.sql
#COPY test_db/load_salaries3.dump /docker-entrypoint-initdb.d/09_load_salaries3.sql
#COPY test_db/show_elapsed.sql /docker-entrypoint-initdb.d/10_show_elapsed.sql
#COPY test_db/*.sql /docker-entrypoint-initdb.d/
#COPY test_db/*.dump /docker-entrypoint-initdb.d/
COPY test_db/employees.sql /docker-entrypoint-initdb.d

# Set the root password
ENV MYSQL_ROOT_PASSWORD example