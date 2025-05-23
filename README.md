# Registration System

## Overview
Spring Boot REST API for managing users with validation and whitelist enforcement for `personID`. Supports CRUD operations and returns consistent JSON error responses.

## Technologies
- Java 17, Spring Boot, Spring Web, Spring Data JPA
- MySQL, Lombok, Jakarta Validation
- JUnit 5, RestAssured, Testcontainers

## Getting Started

### Requirements
- JDK 17+
- MySQL 8+ (or Docker)

### Setup
1. **Start MySQL** (adjust credentials in `application.properties`):
   ```bash
   docker run --name registration_db -e MYSQL_DATABASE=registration_db \
       -e MYSQL_USER=user -e MYSQL_PASSWORD=password \
       -e MYSQL_ROOT_PASSWORD=rootpassword -p 3306:3306 -d mysql:8
   ```

2. **Import `dataPersonId.txt`** into `src/main/resources/` (one ID per line).
3. **Run app**:
   ```bash
   mvn spring-boot:run
   ```

## API Examples (Postman)

### Create User
```http
POST /api/v1/users
Content-Type: application/json
{
  "name": "Jan",
  "surname": "Novák",
  "personID": "abc123def456"
}
```

### Update User
```http
PUT /api/v1/users/{id}
Content-Type: application/json
{
  "name": "Jan",
  "surname": "Nový"
}
```

### Get Users
```http
GET /api/v1/users?detail=true
```

### Error Response
```json
{
  "timestamp": "2025-05-23T12:00:00",
  "status": 400,
  "error": "Validation failed",
  "details": "name: must not be blank"
}
```

## Validation & Errors
- Bean Validation (`@NotBlank`, `@Size`) in DTOs
- Custom `PersonIdValidator` checks whitelist
- Exceptions: `UserNotFound`, `UserAlreadyExists`, `PersonIdNotAllowed`
- Global handler returns `ApiErrorResponse`: `status`, `error`, `details`, `timestamp`

## Project Structure
- `controller` – REST endpoints
- `service` – business logic
- `repository` – database access
- `model` – JPA entity
- `dto` – request/response objects
- `mapper` – DTO/entity conversion
- `exception` – custom exceptions, global handler
- `validation` – whitelist validator
