package com.example.itemservice.repository;

import com.example.itemservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Item entity.
 * Provides CRUD operations for the Item entity using Spring Data JPA.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Spring Data JPA will automatically implement basic CRUD operations
} 