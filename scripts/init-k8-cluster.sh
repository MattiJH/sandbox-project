#!/bin/sh

NAMESPACE="item-service"

SECRET_NAME="item-management-mariadb"

# TODO get fron env var
USERNAME="itemuser"
PASSWORD="itempass"

# Check if the namespace exists
if ! kubectl get namespace "$NAMESPACE" >/dev/null 2>&1; then
  echo "Namespace '$NAMESPACE' does not exist. Creating..."
  kubectl create namespace "$NAMESPACE"
fi

kubectl config set-context --current --namespace="$NAMESPACE"

# Check if the secret exists and delete if it does
if kubectl get secret "$SECRET_NAME" - >/dev/null 2>&1; then
  kubectl delete secret "$SECRET_NAME" -n "$NAMESPACE"
fi

# Create new secret from literals

kubectl create secret generic "$SECRET_NAME"  --from-literal=user="$USERNAME"   --from-literal=password="$PASSWORD" 

