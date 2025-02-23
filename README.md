# Item Management System

A full-stack web application for managing items, built with Spring Boot, Angular, and MariaDB. The application demonstrates a modern microservices architecture with containerized services.

## Features

- View, create, update, and delete items
- RESTful API endpoints with validation
- Responsive UI with Bootstrap 5
- Containerized deployment with Docker
- Comprehensive test coverage:
  - End-to-end testing with Playwright (Chrome, Firefox, Safari)
  - JUnit 5 backend tests
  - Angular component tests
- Automated CI/CD pipeline with GitHub Actions:
  - Parallel test execution
  - Docker container testing
  - Test artifacts retention
  - Caching for faster builds

## Tech Stack

- **Frontend**: Angular 17, Bootstrap 5, TypeScript, Express.js
- **Backend**: Spring Boot 3.2.2, Java 17, JPA/Hibernate
- **Database**: MariaDB 10.6 (Bitnami Chart)
- **Testing**: Playwright, JUnit 5, Karma/Jasmine
- **Infrastructure**: Docker, Docker Compose, Helm, AWS (EKS, VPC, RDS)
- **CI/CD**: GitHub Actions

## API Endpoints

| Method | Endpoint | Description | Response Codes |
|--------|----------|-------------|----------------|
| GET    | /api/items | Get all items | 200 OK |
| GET    | /api/items/{id} | Get item by ID | 200 OK, 404 Not Found |
| POST   | /api/items | Create new item | 201 Created |
| PUT    | /api/items/{id} | Update item | 200 OK, 404 Not Found |
| DELETE | /api/items/{id} | Delete item | 204 No Content, 404 Not Found |

## Project Structure

```
.
├── item-service/          # Spring Boot backend service
├── ui/                    # Angular frontend application
├── e2e/                  # Playwright end-to-end tests
└── iac/                  # Infrastructure as Code
    ├── helm/             # Kubernetes Helm charts
    ├── terraform/        # AWS infrastructure
    └── scripts/          # Deployment scripts
```

## Getting Started

### Prerequisites

- Docker and Docker Compose
- Node.js 18+
- Java 17 (Temurin distribution)
- (Optional) Helm 3.x and kubectl for Kubernetes deployment
- (Optional) Terraform for AWS infrastructure

### Local Development

1. Start the services:
```bash
docker-compose up -d
```

2. Access the applications:
- Frontend: http://localhost
- Backend API: http://localhost:8080
- Database: localhost:3306

## Testing

### E2E Tests
```bash
cd e2e
npm install
npm test                    # Run all tests
npm run test:ui            # Run with UI mode
npm run show-report        # Show test report
```

Test features:
- Cross-browser testing (Chrome, Firefox, Safari)
- Automatic screenshots on failure
- HTML test reports
- Video recording of failed tests

### Backend Tests
```bash
cd item-service
./mvnw test
```

### Frontend Tests
```bash
cd ui
npm run test:ci            # Run tests in CI mode
```

## Continuous Integration

The project uses GitHub Actions for CI/CD with three main jobs:

1. **Frontend Tests**:
   - Runs Angular unit tests
   - Uses Node.js 18.x
   - Caches npm dependencies

2. **Backend Tests**:
   - Runs Java unit tests
   - Uses JDK 17 (Temurin)
   - Caches Maven dependencies

3. **E2E Tests**:
   - Runs after frontend and backend tests pass
   - Sets up Docker environment
   - Executes Playwright tests across browsers
   - Stores test reports and screenshots
   - Retains artifacts for 30 days

## Production Deployment

### Infrastructure Setup

1. Configure AWS infrastructure:
```bash
cd iac/terraform
terraform init
terraform apply
```

### Application Deployment

1. Configure Helm chart values in `iac/helm/item-management/values.yaml`:
- Update image repositories
- Configure resource limits
- Set environment variables

2. Deploy using Helm:
```bash
cd iac/scripts
./deploy.sh
```

### Kubernetes Resources

The Helm chart deploys:
- Frontend service (LoadBalancer)
- Backend service (ClusterIP)
- MariaDB database (Bitnami chart)
- Configurable replicas and resources
- Kubernetes secrets for database credentials

For detailed infrastructure documentation, see:
- [Infrastructure Guide](iac/README.md)
- [Helm Chart Reference](iac/helm/item-management/README.md)
- [Terraform Configuration](iac/terraform/README.md)

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add tests for new features
4. Ensure all tests pass
5. Create a Pull Request

## License

[Add your license information here]