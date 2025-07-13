# Patient Management System

This repository contains a microservices-based Patient Management System, designed to manage patient information and billing operations. The project is organized into two main services:

- **patient-service**: Handles patient data management (CRUD operations, validation, etc.)
- **billing-service**: Manages billing-related operations for patients

## Technologies Used

- Java 17+
- Spring Boot
- Maven
- Docker (for patient-service)

## Project Structure

```
patient-management/
  ├── patient-service/      # Patient data management microservice
  └── billing-service/      # Billing operations microservice
```

### patient-service

- REST API for managing patients
- Data validation and exception handling
- Dockerfile for containerization
- Sample data in `src/main/resources/data.sql`

### billing-service

- Handles billing logic (details in code)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker (optional, for running patient-service in a container)

### Running Locally

#### 1. Clone the repository

```bash
git clone <repo-url>
cd patient-management
```

#### 2. Build and Run patient-service

```bash
cd patient-service
./mvnw spring-boot:run
```

Or build the JAR:

```bash
./mvnw clean package
java -jar target/patient-service-*.jar
```

Or run with Docker:

```bash
docker build -t patient-service .
docker run -p 8080:8080 patient-service
```

#### 3. Build and Run billing-service

```bash
cd ../billing-service
./mvnw spring-boot:run
```

Or build the JAR:

```bash
./mvnw clean package
java -jar target/billing-service-*.jar
```

## API Endpoints

### patient-service

- Base URL: `http://localhost:8080/`
- Main endpoints are defined in `com.patientmanagement.patientservice.controller.PatientController`

### billing-service

- Base URL: `http://localhost:8081/` (default, check `application.properties`)

## Database

- patient-service uses an embedded database (see `data.sql` for sample data)

## Customization

- Configuration files: `src/main/resources/application.properties`
- Add or modify sample data in `patient-service/src/main/resources/data.sql`

## 👨‍💻 Author

**Mehedi Hasan Akash**

- GitHub: [@m-akash](https://github.com/m-akash)
- LinkedIn: [Mehedi Hasan Akash](https://www.linkedin.com/in/mehedi-hasan-akash/)
