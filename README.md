# 📘 Student Management System – Spring Boot Project

A complete, production-level **Student Management System** built using **Spring Boot, Spring Security, JPA, MySQL**, and following industry-standard architecture.  
This project manages **Students, Teachers, and Courses** with full CRUD operations, validation, pagination, searching, and secured role-based access.

---

## 🚀 Project Overview

The system provides a secure and efficient way to manage:

- 👨‍🎓 Students  
- 👨‍🏫 Teachers  
- 📚 Courses  
- 🔗 Many-to-many relationships  
- 🔍 Searching & Pagination  
- 🔐 Role-based secured APIs  

---

## 🧩 Features

### **👨‍🎓 Student Module**
- Create / Update / Delete student  
- Search by name, email  
- Pagination & sorting  
- Many-to-many mapping with courses  
- Delete student (ADMIN only)  

### **👨‍🏫 Teacher Module**
- Create / Update / Delete teacher  
- Search by specialization or name  
- Many-to-many mapping with courses  
- Delete teacher (ADMIN only)  

### **📚 Course Module**
- Create / Update / Delete course  
- Assign students & teachers  
- Search by course name or code  
- Delete course (ADMIN only)  

---

## 🔐 Security

Spring Security with:

### ✔ In-Memory Users

| Username | Password   | Role  |
|----------|-------------|--------|
| admin    | admin123    | ADMIN |
| user     | user123     | USER  |

### ✔ Endpoint Access Control
| Endpoint | Access |
|----------|--------|
| `/public/**` | Permit All |
| `/api/student/**` | USER, ADMIN |
| `/api/teacher/**` | USER, ADMIN |
| `/api/courses/**` | USER, ADMIN |
| `DELETE` endpoints | ADMIN only |

Security implemented using:
- SecurityFilterChain  
- PasswordEncoder (BCrypt)  
- HttpSecurity authorization rules  
- Method level security (`@PreAuthorize`)  

---

## 🏗 High-Level Architecture

```
                 +------------------------------+
                 |       Client / Postman       |
                 +--------------+---------------+
                                |
                                v
                    +-----------+-----------+
                    |      Spring Boot      |
                    | Student Management App|
                    +-----------+-----------+
                                |
        ----------------------------------------------------
        |                        |                         |
        v                        v                         v
 +-------------+         +----------------+        +------------------+
 | Student API |         | Teacher API    |        | Course API       |
 +-------------+         +----------------+        +------------------+
        |                        |                         |
        ------------------- Database Layer -----------------
                  (MySQL + JPA + Hibernate)
```

---

## 🗃 Database Design

### **Student**
- id  
- name  
- age  
- email  
- courses (Many-to-Many)

### **Teacher**
- id  
- name  
- specialization  
- courses (Many-to-Many)

### **Course**
- id  
- name  
- courseCode  
- students (Many-to-Many)  
- teachers (Many-to-Many)  

---

## 📦 Technologies Used

- Java 17  
- Spring Boot 3.5  
- Spring Security  
- Spring Data JPA  
- Hibernate  
- MySQL  
- Maven  
- Lombok  

---

## 📌 API Endpoints

### **📚 Course API**
```
POST    /api/courses/create
GET     /api/courses/find/course/name/{name}
GET     /api/courses/find/all/course
GET     /api/courses/find/CourseCode/{code}
PUT     /api/courses/update/{id}
DELETE  /api/courses/delete/{id}     (ADMIN only)
```

### **👨‍🎓 Student API**
```
POST    /api/student/create
GET     /api/student/all/student
GET     /api/student/get/student/{id}
PUT     /api/student/update/student/{id}
GET     /api/student/search/student?name=&email=
GET     /api/student/page?page=&size=&sortBy=
DELETE  /api/student/delete/{id}     (ADMIN only)
```

### **👨‍🏫 Teacher API**
```
POST    /api/teacher/create
GET     /api/teacher/find/teacher/name/{name}
GET     /api/teacher/find/all/teacher
GET     /api/teacher/find/Specialization/{specialization}
PUT     /api/teacher/update/{id}
DELETE  /api/teacher/delete/{id}     (ADMIN only)
GET     /api/teacher/find/Specialization/name?specialization=&name=
```

---

## ⚙️ How to Run the Project

### **1️⃣ Clone the repository**
```
git clone https://github.com/YOUR_USERNAME/StudentManagementSystem.git
```

### **2️⃣ Configure MySQL in `application.properties`**
```
spring.datasource.url=jdbc:mysql://localhost:3306/studentManagement
spring.datasource.username=root
spring.datasource.password=root
```

### **3️⃣ Start MySQL Server**

### **4️⃣ Run Using Maven**
```
mvn spring-boot:run
```

### Application will run at:
👉 **http://localhost:8080**

---

## 🛡 Exception Handling

Custom exceptions:

- **BadRequestException**  
- **ResourceNotFoundException**

Handled globally using:

```
@ControllerAdvice
@ExceptionHandler
```

Error response structure:

```json
{
  "dateTime": "",
  "message": "",
  "error": "",
  "path": ""
}
```

---

## 🎯 Why This Project Is Strong 

- ✔ Real-world CRUD application  
- ✔ Many-to-Many entity mapping  
- ✔ Pagination, sorting, search filters  
- ✔ Spring Security with roles  
- ✔ Validations + Global Exception Handling  
- ✔ Proper layered architecture  
- ✔ Clean and production-style code  

Perfect for Java Developer, Backend Engineer, and Spring Boot Developer roles.

---

## 🙌 Author

**Gaur Gopal**  
Java Backend Developer | Spring Boot | JPA | Security

---

