# Order Management Service

A microservice for managing orders using hexagonal architecture and Spring Boot.

## Architecture

This project implements hexagonal architecture (ports and adapters) with:

- **Domain Layer**: Core business logic and models
- **Application Layer**: Use cases and service implementations
- **Infrastructure Layer**: Technical implementations (validation, mapping)
- **Adapters Layer**: External interactions (REST API, Database)

## Technical Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database
- Liquibase
- Lombok
- MapStruct

## Features

- Order management (CRUD operations)
- Order items management
- Order status tracking
- Data validation
- Database migrations with Liquibase
- Automatic ID generation
- Order number and item number tracking

## Database Schema

- Orders table with order tracking
- Order items with product details
- Sequences for ID generation
- Foreign key relationships

## API Endpoints

GET    /api/orders           - Get all orders
GET    /api/orders/{id}      - Get order by ID
POST   /api/orders           - Create new order
PUT    /api/orders/{id}      - Update order
DELETE /api/orders/{id}      - Delete order

## Getting Started

### Prerequisites

- JDK 17
- Maven 3.8+

### Building the Project

```bash
mvn clean install
```

### Running the Application

```bash
mvn spring-boot:run
```

The application will start on http://localhost:8080

### Database
H2 Console is available at http://localhost:8080/h2-console

Connection details:

- JDBC URL: jdbc:h2:mem:orderdb
- Username: sa
- Password: password

## Project Structure
```
src/main/java/com/orderms/
├── adapter/
│   ├── api/              # REST controllers and DTOs
│   └── persistence/      # Database entities and repositories
├── application/
│   ├── port/            # Input/Output ports
│   └── service/         # Service implementations
├── domain/
│   ├── model/          # Domain models
│   └── exception/      # Domain exceptions
└── infrastructure/
    ├── mapper/         # Object mappers
    └── validator/      # Validation logic
```
## Database Migrations
Database changes are managed through Liquibase and can be found in:

```
src/main/resources/db/changelog/
```


This README provides:
- Project overview
- Technical details
- Setup instructions
- API documentation
- Project structure
- Database information