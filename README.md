# 📝 Journal App – Spring Boot Project

This is a backend test project built using **Spring Boot**, demonstrating a wide range of enterprise-grade backend features such as RESTful APIs, MongoDB integration, security, email functionality, logging, and more.

---

## 🚀 Features Implemented

### 🌐 RESTful APIs
- Built and tested CRUD APIs using `@RestController`, `@RequestMapping`, and HTTP method annotations.
- Connected APIs to MongoDB using Spring Data MongoDB and Atlas Cloud for persistence.

### 📦 Build & Dependency Management
- Used **Maven** for project management, dependency resolution, and lifecycle management.

### 🧠 Core Spring Concepts
- Implemented **Inversion of Control (IoC)** and **Dependency Injection (DI)** using annotations like `@Component`, `@Autowired`, etc.

### 🔐 Security
- Added **Spring Security** for both authentication and role-based authorization.
- Protected sensitive endpoints and enabled basic HTTP authentication.

### 💌 Email Service
- Configured Gmail SMTP to send emails via `JavaMailSender`.

### 📄 Logging
- Used **SLF4J with Logback** for logging.
- Redirected logs to a file via `application.yml` configuration.

### 🔍 Query Building
- Built dynamic queries using both **QueryDSL** and **Criteria API**.

### ⚙️ Integrations
- ✅ **Redis** – Implemented caching for improved performance.
- ✅ **Kafka** – Enabled asynchronous communication between services.
- ✅ **Swagger** – Auto-generated API documentation accessible via Swagger UI.

### ⏰ Task Scheduling
- Scheduled automated tasks using `@Scheduled` and cron expressions.

### 🧩 Utilities
- Leveraged **Lombok** to reduce boilerplate in models and services.
- Used `ResponseEntity` for well-structured and customizable HTTP responses.

---

## ✅ Tools & Technologies
Spring Boot, Maven, MongoDB, Redis, Kafka, Swagger, SLF4J, Lombok, Spring Security, JavaMail, Postman

---

> This project reflects hands-on experience with real-world backend challenges and serves as a robust foundation for microservices or cloud-native applications.
