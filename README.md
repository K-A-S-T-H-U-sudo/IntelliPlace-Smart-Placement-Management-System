# IntelliPlace – Smart Campus Placement Management System

## 📌 Project Overview

**IntelliPlace** is a Java-based desktop application designed to simplify and manage campus placement activities in educational institutions. It provides a centralized platform to manage student details, company information, job opportunities, student applications, and placement reports.

The application uses JavaFX for its graphical user interface and MySQL for database management. JDBC connects the Java application with the MySQL database.

## 🎯 Objectives

* To simplify campus placement management.
* To maintain student and company information.
* To manage job opportunities and student applications.
* To track application status and selection details.
* To generate placement statistics and reports.
* To apply Object-Oriented Programming (OOP) concepts in a real-world project.

## 🛠️ Technologies Used

* **Programming Language:** Java
* **User Interface:** JavaFX
* **Database:** MySQL
* **Database Connectivity:** JDBC
* **IDE:** Eclipse
* **Database Tool:** MySQL Workbench
* **Java Version:** Java 25
* **JavaFX Version:** JavaFX 27

## ✨ Features

### 1. Login Module

Provides username and password authentication for accessing the application.

### 2. Dashboard Module

Displays placement information and provides navigation to different modules.

### 3. Student Management

* Add student details
* View student records
* Delete student records
* Store department, CGPA, email, phone number, and skills

### 4. Company Management

* Add company details
* View company information
* Delete company records

### 5. Job Management

* Add and view job opportunities
* Maintain job roles and salary packages
* Store minimum CGPA and required skills

### 6. Application Management

* Add student job applications
* View application records
* Update application status
* Delete application records
* Track Applied, Shortlisted, Selected, and Rejected applications

### 7. Placement Reports

Displays important placement statistics, including:

* Total Students
* Total Companies
* Total Jobs
* Total Applications
* Selected Students

## 🏗️ Project Structure

```text
IntelliPlace/
├── src/
│   └── com.intelliplace/
│       ├── Main.java
│       ├── DBTest.java
│       ├── model/
│       │   ├── Student.java
│       │   ├── Company.java
│       │   ├── Job.java
│       │   └── Application.java
│       ├── dao/
│       │   ├── StudentDAO.java
│       │   ├── CompanyDAO.java
│       │   ├── JobDAO.java
│       │   └── ApplicationDAO.java
│       ├── controller/
│       │   ├── LoginController.java
│       │   ├── DashboardController.java
│       │   ├── StudentController.java
│       │   ├── CompanyController.java
│       │   ├── JobController.java
│       │   ├── ApplicationController.java
│       │   └── ReportsController.java
│       └── util/
│           └── DBConnection.java
```

*Note: The structure above represents the main project components. Package folders may appear separately in Eclipse.*

## 🗄️ Database Configuration

The project uses a MySQL database named `intelliplace`.

Main tables:

* `users`
* `students`
* `companies`
* `jobs`
* `applications`

### Database Setup

1. Install MySQL Server and MySQL Workbench.
2. Create a database named `intelliplace`.
3. Create the required tables with their primary keys and foreign keys.
4. Open `DBConnection.java`.
5. Configure your MySQL username and password.

Example database connection:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/intelliplace";

private static final String USER = "root";

private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";
```

Replace `YOUR_MYSQL_PASSWORD` with your own MySQL password. Never upload your actual database password to GitHub.

## ▶️ How to Run the Project

1. Install Java JDK and Eclipse IDE.
2. Install JavaFX SDK and MySQL Server.
3. Clone or download this repository.
4. Import the project into Eclipse as a Java project.
5. Add the JavaFX SDK libraries to the project.
6. Add MySQL Connector/J to the project's build path.
7. Create the `intelliplace` database and required tables.
8. Update the database credentials in `DBConnection.java`.
9. Configure the JavaFX VM arguments if required:

```text
--module-path "D:\javafx-sdk-27\lib" --add-modules javafx.controls,javafx.fxml
```

10. Run `Main.java` to launch the application.

**Note:** Update the JavaFX library path according to your computer's installation location.

## 💡 OOP Concepts Used

* **Classes and Objects:** Represent students, companies, jobs, and applications.
* **Encapsulation:** Uses private fields and public getter and setter methods.
* **Abstraction:** Separates data access and user-interface logic.
* **Polymorphism:** Can be demonstrated through method overriding and interface implementations.
* **Exception Handling:** Handles database and application errors.
* **Packages:** Organizes the application into model, DAO, controller, and utility components.

## 🚀 Future Enhancements

* Resume upload and management
* Automated student eligibility checking
* Email and application deadline notifications
* Advanced placement analytics and visualizations
* PDF and Excel report generation
* Web and mobile application support
* Machine learning-based placement prediction
* Cloud database and automated backups

## 🎓 Project Purpose

This project was developed as an academic micro project to demonstrate Java programming, JavaFX GUI development, JDBC connectivity, MySQL database operations, and Object-Oriented Programming concepts.


This project is intended for educational and learning purposes. Add an appropriate open-source license if you want others to reuse, modify, and distribute the code.
