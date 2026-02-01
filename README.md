# Academic Result Portal

A full-stack web application for managing student records and semester results. Designed to serve as a mini-project demonstrating CRUD operations, authentication, and report generation using the Spring ecosystem.

## Tech Stack
* **Backend:** Java (Spring Boot, Spring MVC, Spring Data JPA)
* **Database:** MySQL
* **Frontend:** Thymeleaf, Bootstrap 5 (For Demonstration)
* **Security:** Spring Security (In-Memory Authentication)
* **Tools:** Maven, IntelliJ IDEA

## Key Features
* **Admin Dashboard:** Secure login system to prevent unauthorized access to sensitive data.
* **Student Management:** Add, update, and store detailed student profiles (including personal details like address, parents' names, etc.).
* **Result Processing:** Complex mapping (One-to-Many) allowing a single student to have results for multiple semesters.
* **Public Search:** Students can search for their marks using their Roll Number and Date of Birth without logging in.
* **Automated Grading:** The system automatically calculates totals, percentages, and assigns grades based on marks obtained.


## Configuration & Setup

### 1. Database Setup
Ensure MySQL is running. Create a database named `academic_result_portal` (or update the properties file).
```sql
CREATE DATABASE academic_result_portal;
```

### 2. Application Properties
Update src/main/resources/application.properties to connect to your database:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/academic_result_portal
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```
### 3. Login Credentials
Admin Login URL: http://localhost:8082/admin-login

Username: admin

Password: admiin@10

### How to Run
Open the project in IntelliJ IDEA.

Wait for Maven dependencies to download.

Run AcademicResultPortalApplication.java.

Open http://localhost:8082 in your browser.
