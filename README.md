# Task Planner Backend

## 📌 Overview
This is the backend service for the **Task Planner Application** built using **Spring Boot (v3.3.4)**, **Hibernate JPA**, and **MySQL**.

## 🚀 Technologies Used
- Java 17
- Spring Boot 3.3.4
- Spring Data JPA (Hibernate ORM 6.5.3.Final)
- MySQL (Connector/J)
- Maven

Build Project
mvn clean install

Run Application
mvn spring-boot:run


The server will start at:
http://localhost:8080
📡 API Endpoints (Sample)

GET /tasks → Fetch all tasks

POST /tasks → Create a new task

PUT /tasks/{id} → Update task

DELETE /tasks/{id} → Delete task

