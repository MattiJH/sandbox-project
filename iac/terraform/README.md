# Terraform Infrastructure

This directory contains Terraform configurations for the Item Management System infrastructure.

## Documentation

Documentation is automatically generated using terraform-docs. To update the documentation:

1. Install terraform-docs:
   ```bash
   # MacOS
   brew install terraform-docs

   # Windows (using Chocolatey)
   choco install terraform-docs
   ```

2. Generate documentation:
   ```bash
   ./generate-docs.sh
   ```

## Generated Documentation

[Documentation will be inserted here by terraform-docs] 
<!-- BEGIN_TF_DOCS -->
# Item Management System Infrastructure

This Terraform configuration manages the AWS infrastructure for the Item Management System.
It sets up VPC, EKS cluster, and supporting resources.

## Requirements
## Requirements

| Name | Version |
|------|---------|
| <a name="requirement_aws"></a> [aws](#requirement\_aws) | ~> 4.0 |

## Providers
## Providers

No providers.

## Modules


## Resources
## Resources

No resources.

## Inputs
## Inputs

| Name | Description | Type | Default | Required |
|------|-------------|------|---------|:--------:|
| <a name="input_aws_region"></a> [aws\_region](#input\_aws\_region) | AWS region where resources will be created | `string` | `"us-west-2"` | no |
| <a name="input_environment"></a> [environment](#input\_environment) | Environment name (production, staging, development) | `string` | `"production"` | no |

## Outputs
## Outputs

No outputs.
<!-- END_TF_DOCS -->