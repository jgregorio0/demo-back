# Demo back sample

Demo back is a sample project for a RESTful API.

-----

## 🚀 Getting Started

### ⚙️ Prerequisites

This project uses the following key technologies:

* **Java 25**
* **Spring Boot 3.5.6**
* **Gradle 9.1.0**
* **H2 Database**

### 💻 Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/jgregorio0/demo-back.git
    cd demo-back
    ```

2. Configure environment variables:

    ```.env
    # Spring Boot Profile
    SPRING_PROFILES_ACTIVE=development
    
    # Oracle Database Connection
    DB_URL=jdbc:oracle:thin:@//<host>:<port>/<database>
    DB_USERNAME=<your_db_username>
    DB_PASSWORD=<your_db_password>
    ```

3. Build the project:

    ```bash
    ./gradlew build
    ```

4. Run the application:

    ```bash
    ./gradlew bootRun 
    ```

The application will start on `http://localhost:8080/demo-back`.

-----

## 💡 Usage

### Example endpoint:

**`GET status`**
Gets status server.

```bash
    curl -X GET http://localhost:8080/demo-back/actuator/health -H "Accept: application/json"
```

### Access Swagger UI

Swagger UI will be available at the following URLs:

- Local: http://localhost:8080/demo-back/swagger-ui/index.html

-----

## 🤝 Contributing

We welcome contributions to this project! To ensure a smooth and efficient collaboration, please
read our [Contributing Guide](CONTRIBUTING.md) before you start. It is mandatory to follow the
code style and workflow guidelines described in it.
