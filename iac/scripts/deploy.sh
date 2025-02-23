#!/bin/bash

# Build Docker images
docker-compose build

# Push images to registry
docker push your-registry/item-service:latest
docker push your-registry/item-ui:latest

# Add Bitnami repo for MariaDB dependency
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update

# Deploy using Helm
helm upgrade --install item-management ../helm/item-management \
  --namespace item-management \
  --create-namespace \
  --values ../helm/item-management/values.yaml 