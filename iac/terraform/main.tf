/**
 * # Item Management System Infrastructure
 *
 * This Terraform configuration manages the AWS infrastructure for the Item Management System.
 * It sets up VPC, EKS cluster, and supporting resources.
 */

terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.0"
    }
  }
}

provider "aws" {
  region = var.aws_region
}

module "vpc" {
  source = "./modules/vpc"
  # VPC configuration variables
}

module "eks" {
  source = "./modules/eks"
  # EKS configuration variables
  depends_on = [module.vpc]
}

module "rds" {
  source = "./modules/rds"
  # RDS configuration variables
  depends_on = [module.vpc]
} 