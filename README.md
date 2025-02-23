# Item Management System

A full-stack web application for managing items, built with Spring Boot, Angular, and MariaDB. The application demonstrates a modern microservices architecture with containerized services and Infrastructure as Code (IaC).

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
   - Comprehensive unit tests
   - Javadoc documentation

3. **Database (MariaDB)**
   - Persistent data storage
   - Automatic schema generation
   - Docker volume for data persistence

4. **Infrastructure as Code**
   - Helm charts for Kubernetes deployment
   - Terraform for cloud infrastructure
   - Automated documentation
   - Infrastructure version control

## Features

### Item Management
- View list of items
- View item details
- Create new items
- Update existing items
- Delete items

### API Endpoints

| Method | Endpoint | Description | Response Codes |
|--------|----------|-------------|----------------|
| GET    | /api/items | Get all items | 200 OK |
| GET    | /api/items/{id} | Get item by ID | 200 OK, 404 Not Found |
| POST   | /api/items | Create new item | 201 Created |
| PUT    | /api/items/{id} | Update item | 200 OK, 404 Not Found |
| DELETE | /api/items/{id} | Delete item | 204 No Content, 404 Not Found |

## Technical Stack

### Backend
- Java 17
- Spring Boot 3.2.2
- Spring Data JPA
- MariaDB Connector
- Maven for dependency management
- JUnit 5 for testing
- Mockito for mocking

### Frontend
- Angular 17
- Bootstrap 5
- TypeScript
- Express.js (production server)
- Node.js

### Infrastructure
- Docker & Docker Compose
- Kubernetes (via Helm)
- Terraform for cloud resources
- AWS cloud provider

## Project Structure

```
.
├── item-service/          # Backend service
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── ui/                    # Frontend application
│   ├── src/
│   ├── package.json
│   └── Dockerfile
└── iac/                   # Infrastructure as Code
    ├── helm/              # Kubernetes Helm charts
    ├── terraform/         # Terraform configurations
    └── scripts/           # Deployment scripts
```

## Documentation

### API Documentation
- Comprehensive Javadoc for backend services
- REST API endpoint documentation
- Response status codes and examples

### Infrastructure Documentation
- Terraform documentation (auto-generated)
- Helm chart documentation
- Deployment guides

## Setup and Installation

### Prerequisites
- Docker and Docker Compose
- Kubernetes cluster
- Helm 3.x
- Terraform
- AWS CLI configured

### Local Development Setup

1. Start the development environment:
```bash
docker-compose up -d
```

2. Access the applications:
- Frontend: http://localhost
- Backend API: http://localhost:8080
- Database: localhost:3306

### Production Deployment

1. Configure cloud infrastructure:
```bash
cd iac/terraform
terraform init
terraform apply
```

2. Deploy application:
```bash
cd ../scripts
./deploy.sh
```

## Testing

### Backend Tests
```bash
cd item-service
./mvnw test
```

### Frontend Tests
```bash
cd ui
npm test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add tests for new features
4. Ensure all tests pass
5. Add appropriate documentation
6. Create a Pull Request

## License

[Add your license information here]