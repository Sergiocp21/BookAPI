# Book API REST

## 🗃 Table of Contents

- [Description](#description)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#📚-Usage)
- [Project Structure](#📁-Project-Structure)
- [Endpoints](#endpoints)

---

## 🖍 Description

Book API Rest is a simple API for the unique Entity "Books" **Spring Boot**.

## ✨ Features

- Resource management through CRUD operations.
- API documentation with Swagger.
- Structure based on RESTful principles.

## 🛠️ Technologies Used

- **Language:** Java
- **Framework:** Spring Boot
- **Database:** MariaDB
- **Application Server:** Tomcat
- **Dependencies:**
    - Spring Web
    - Spring Data JPA
    - MariaDB Connector
    - Swagger/OpenAPI

## 🚀 Installation

Follow the steps below to run the project locally:

1.**Make sure you have Java JDK 23 installed on your machine**

You can download it [here](https://www.oracle.com/java/technologies/downloads/)

2.**Clone the repository**:
   ```bash
   git clone https://github.com/Sergiocp21/BookAPI
   ```
3.**Import the project into IntelliJ IDEA/Eclipse or your favourite Java IDE**

4.**Configure the database**:
- Download the .sql for a faster creation of the database with examples [here](https://drive.google.com/file/d/1rnELKcSx16kgZCIv3NJMXKf8iO-TgNJi/view?usp=sharing)

- Update the `application.properties` file with your credentials:
  ```properties
  spring.datasource.url=jdbc:mariadb://localhost:3306/prueba_tecnica_biblioteca
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  ```
5.**Run the application** from your IDE or with the command:
   ```bash
   ./mvnw spring-boot:run
   ```
6.Access the API at `http://localhost:8080/api/books`.

## 📚 Usage

The API includes the following endpoints for the `Books` table.

### **Some of available Endpoints**

| Method | Endpoint          | Description                 |
| ------ | ----------------- | --------------------------- |
| GET    | `/api/books`      | Retrieves all books.        |
| GET    | `/api/books/{id}` | Retrieves a book by ID.     |
| POST   | `/api/books`      | Creates a new book.         |
| PUT    | `/api/books/{id}` | Updates an existing book.   |
| DELETE | `/api/books/{id}` | Deletes a book by ID.       |


### **Example JSON for GET**

```json
{
  "id": 0,
  "title": "The Random Book",
  "author": "Paco",
  "isRead": false,
  "createdAt": "2023-01-24"
}
```

### **Example JSON for POST**

```json
{
  "title": "The Random Book 2",
  "author": "Paco",
  "createdAt": "2024-01-24"
}
```

### Documentation

You can view all the endpoints at: `http://localhost:8080/swagger-ui/index.html` (make sure that the application server is running to see it)

## 📁 Project Structure

```plaintext
src/main/java/com/sergiocarvajal/prueba_back_iberpixel
│
├── Controller
│   └── BookController.java       # REST Controller
├── Model
│   └── Book.java                 # JPA Entity
├── Repository
│   └── BookRepository.java       # JPA Repository
├── Service
│   └── BookService.java          # Business Logic
└── PruebaBackIberPixelApplication.java    # Main Class
```

