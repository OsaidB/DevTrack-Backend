# DevTrack Backend

DevTrack is a web application designed for developers to manage tasks and projects effectively. It provides functionality similar to Trello and Jira but is focused solely on development tasks. The backend of this application is built using Spring Boot and provides RESTful APIs for managing projects, tasks, and user roles.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

- User authentication and authorization
- Create and manage projects
- Create and manage tasks within projects
- Assign tasks to developers
- Task statuses: To Do, In Progress, Done, and QA
- Role-based access control (Admin, Frontend Developer, Backend Developer, QA Developer)

## Technologies Used

- Spring Boot 3.3.2
- Spring Data JPA (Hibernate)
- MySQL Database
- Lombok
- Maven
- Java 21

## Getting Started

### Prerequisites

- JDK 21
- MySQL Database
- Maven
- IntelliJ IDEA or any other preferred IDE

### Installation

1. Clone the repository:

```
git clone https://github.com/OsaidB/DevTrack-Backend.git
```

2. Navigate to the project directory:

```sh
cd DevTrack-Backend
```

3. Configure the database in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/devtrack
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Running the Application

1. Build the project using Maven:

```
mvn clean install
```

2. Run the Spring Boot application:

```
mvn spring-boot:run
```

The application should now be running at `http://localhost:8080`.

## API Endpoints

- `GET /api/users` - Retrieve all users
- `POST /api/users` - Create a new user
- `GET /api/users/{id}` - Retrieve a user by ID
- `PUT /api/users/{id}` - Update a user by ID
- `DELETE /api/users/{id}` - Delete a user by ID
- `GET /api/projects` - Retrieve all projects
- `POST /api/projects` - Create a new project
- `GET /api/projects/{id}` - Retrieve a project by ID
- `PUT /api/projects/{id}` - Update a project by ID
- `DELETE /api/projects/{id}` - Delete a project by ID
- `GET /api/tasks` - Retrieve all tasks
- `POST /api/tasks` - Create a new task
- `GET /api/tasks/{id}` - Retrieve a task by ID
- `PUT /api/tasks/{id}` - Update a task by ID
- `DELETE /api/tasks/{id}` - Delete a task by ID

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements, bug fixes, or documentation updates.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
