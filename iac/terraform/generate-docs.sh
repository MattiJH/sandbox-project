#!/bin/bash

# Check if terraform-docs is installed
if ! command -v terraform-docs &> /dev/null; then
    echo "terraform-docs is not installed. Please install it first."
    echo "Visit: https://terraform-docs.io/user-guide/installation/"
    exit 1
fi

# Generate documentation
terraform-docs .

echo "Terraform documentation has been generated successfully!" 