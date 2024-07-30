## Getting Started

### Prerequisites

- [Eclipse Temurin (AdoptOpenJDK HotSpot) 21.0.4](https://adoptium.net/)
- Maven 3.8.1 or later
- MySQL

### Setup

1. Install Eclipse Temurin JDK:

    ```bash
    sudo apt-get install openjdk-21-jdk
    ```

2. Verify the installation:

    ```bash
    java -version
    ```

    You should see output indicating you are using Eclipse Temurin 21.0.4.

3. Clone the repository:

    ```bash
    git clone https://github.com/yourusername/devtrack-backend.git
    cd devtrack-backend
    ```

4. Configure the MySQL database:

    - Create a database named `devtrack`.
    - Update the `src/main/resources/application.properties` file with your MySQL credentials.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/devtrack
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    ```

5. Build and run the application:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

6. The backend server will be running at `http://localhost:8080`.
