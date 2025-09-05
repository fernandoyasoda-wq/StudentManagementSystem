# Student Management System (Java + MySQL)

A simple console-based application demonstrating CRUD operations on a MySQL database via JDBC.  
Built for IT 3003 mini-project (individual).

## Features
- Add new students
- View all students
- Update a student
- Delete a student
- Search students by name

## Technologies
- Java 21 (works with Java 8+)
- MySQL 8.0
- JDBC (MySQL Connector/J)
- Maven
- IntelliJ IDEA Community

## Project Structure
StudentManagementSystem/
├─ pom.xml
├─ src/main/java/com/studentmgmt/
│  ├─ MainApp.java
│  ├─ db/DBConnection.java
│  ├─ model/Student.java
│  └─ dao/
│     ├─ StudentDAO.java
│     └─ StudentDAOImpl.java
└─ src/main/resources/
└─ db.properties

## Setup Instructions
1. Install MySQL and create the database:
   ```sql
   CREATE DATABASE studentdb;
   USE studentdb;
   CREATE TABLE students (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(100),
     age INT,
     course VARCHAR(100)
   );
2. Update src/main/resources/db.properties with your MySQL username & password.

3. Open project in IntelliJ, rebuild, and run MainApp.java.
   === Student Management ===
1) Add Student
2) View All
3) Update Student
4) Delete Student
5) Search by Name
0) Exit
