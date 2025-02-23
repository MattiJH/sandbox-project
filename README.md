# Item Management System

A full-stack web application for managing items, built with Spring Boot, Angular, and MariaDB. The application demonstrates a modern microservices architecture with containerized services.

## System Architecture

The application consists of three main components:

1. **Frontend (Angular)**
   - Single Page Application
   - Bootstrap for responsive design
   - Express.js server for production deployment
   - Component-based architecture
   - TypeScript for type safety

2. **Backend (Spring Boot)**
   - RESTful API endpoints
   - JPA/Hibernate for data persistence
   - MariaDB integration
   - Containerized deployment

3. **Database (MariaDB)**
   - Persistent data storage
   - Automatic schema generation
   - Docker volume for data persistence

## Features

### Item Management
- View list of items
- View item details
- Create new items
- Update existing items
- Delete items

### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/items | Get all items |
| GET    | /api/items/{id} | Get item by ID |
| POST   | /api/items | Create new item |
| PUT    | /api/items/{id} | Update item |
| DELETE | /api/items/{id} | Delete item |

## Technical Stack

### Backend
- Java 17
- Spring Boot 3.2.2
- Spring Data JPA
- MariaDB Connector
- Maven for dependency management

### Frontend
- Angular 17
- Bootstrap 5
- TypeScript
- Express.js (production server)
- Node.js

### Infrastructure
- Docker
- Docker Compose
- Docker volumes for persistence
- Docker networking

## Project Structure

### Backend Service (item-service)
```
item-service/
├── src/main/java/
│   └── com/example/itemservice/
│       ├── controller/    # REST endpoints
│       ├── model/        # Entity classes
│       ├── repository/   # Data access
│       └── service/      # Business logic
├── src/main/resources/
│   ├── application.properties  # Configuration
│   └── db/
│       └── init.sql      # Database initialization
└── Dockerfile           # Backend container config
```

### Frontend Application (ui)
```
ui/
├── src/
│   ├── app/
│   │   ├── components/   # Angular components
│   │   ├── services/     # API services
│   │   └── models/       # TypeScript interfaces
│   └── assets/          # Static files
├── server.js           # Production Express server
└── Dockerfile         # Frontend container config
```

## Setup and Installation

### Prerequisites
- Docker
- Docker Compose

### Running the Application

1. Clone the repository:
```bash
git clone <repository-url>
cd item-management-system
```

2. Start the application:
```bash
docker-compose up -d
```

The application will be available at:
- Frontend: http://localhost
- Backend API: http://localhost:8080
- Database: localhost:3306

### Development Setup

#### Backend
```bash
cd item-service
./mvnw spring-boot:run
```

#### Frontend
```bash
cd ui
npm install
npm start
```

## Configuration

### Database Configuration
```yaml
# docker-compose.yml
mariadb:
  environment:
    MYSQL_DATABASE: itemdb
    MYSQL_USER: itemuser
    MYSQL_PASSWORD: itempass
```

### Backend Configuration
```properties
# application.properties
spring.datasource.url=jdbc:mariadb://mariadb:3306/itemdb
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Docker Configuration

### Services
- **MariaDB**: Database service with health checks
- **Backend**: Spring Boot application with database dependency
- **Frontend**: Angular application served by Express

### Networking
- All services run on a dedicated Docker network
- Internal service discovery using Docker DNS
- Port mapping for external access

## Development Guidelines

### Backend Development
- Follow RESTful API principles
- Use DTOs for data transfer
- Implement proper error handling
- Write unit tests

### Frontend Development
- Follow Angular best practices
- Use TypeScript features
- Implement proper error handling
- Make components reusable

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

[Add your license information here]