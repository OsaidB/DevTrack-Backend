# DevTrack Backend

This repository contains the backend code for DevTrack, a project management tool for developers. The backend is built with Spring Boot and provides RESTful APIs for managing users, projects, tasks, and more.

## Technologies Used

- **Spring Boot 3.3.2**
- **Spring Data JPA (Hibernate)**
- **MySQL Database**
- **Maven (as a build tool)**
- **Lombok (for reducing boilerplate code)**

## Requirements

- Java Development Kit (JDK) 21
- MySQL Database
- Maven
- IDE (IntelliJ IDEA recommended)

## Getting Started

### Setup MySQL Database

1. Install MySQL and create a new database named `devtrack`.
2. Update the `application.properties` file with your MySQL username and password.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/devtrack
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Build and Run the Project

1. Clone the repository:

```bash
git clone https://github.com/yourusername/DevTrack-Backend.git
cd DevTrack-Backend
```

2. Build the project using Maven:

```bash
mvn clean install
```

3. Run the Spring Boot application:

```bash
mvn spring-boot:run
```

The backend server will start at `http://localhost:8080`.

### Testing the APIs

You can use Postman or any other API testing tool to test the APIs.

#### Example: Create a User

1. Open Postman.
2. Create a new POST request to `http://localhost:8080/api/users`.
3. Set the request body to JSON format and provide user details:

```json
{
  "username": "johndoe",
  "email": "johndoe@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "role": "frontend_developer",
  "isAdmin": false
}
```

4. Send the request and verify the response.

## Project Structure

```plaintext
src/main/java/bisan/internship/devtrack
├── config          # Configuration classes
├── controller      # REST controllers
├── dto             # Data Transfer Objects (DTOs)
├── exception       # Custom exception handling
├── mapper          # Mapper classes for converting between entities and DTOs
├── model
│   └── entity      # JPA entities
├── repository      # Spring Data JPA repositories
└── service         # Service layer interfaces and implementations
```

## Contributing

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

## License

This project is licensed under the MIT License.
