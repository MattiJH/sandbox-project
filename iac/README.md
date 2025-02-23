# Infrastructure as Code (IaC)

This directory contains infrastructure configuration for the Item Management System.

## Directory Structure

```
iac/
├── helm/                # Helm charts
│   └── item-management/
│       ├── Chart.yaml
│       ├── values.yaml
│       └── templates/
├── terraform/           # Terraform configurations
│   ├── main.tf
│   ├── variables.tf
│   └── modules/
└── scripts/            # Deployment scripts
    └── deploy.sh
```

## Helm Configuration

The application is deployed using Helm charts:
- Backend service deployment
- Frontend UI deployment
- MariaDB (using Bitnami chart)
- Services configuration

### Chart Dependencies
- MariaDB: Bitnami MariaDB chart

## Terraform Configuration

Manages cloud infrastructure:
- VPC and networking
- EKS cluster
- Security groups

## Deployment

1. Initialize Terraform:
```bash
cd terraform
terraform init
terraform apply
```

2. Deploy using Helm:
```bash
cd ../scripts
./deploy.sh
```

## Configuration

Update the following files for your environment:
- helm/item-management/values.yaml
- terraform/variables.tf
- scripts/deploy.sh

## Security

Sensitive information is managed through:
- Kubernetes secrets (managed by Helm)
- AWS KMS
- Environment variables 