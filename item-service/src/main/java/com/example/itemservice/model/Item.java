package com.example.itemservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entity class representing an Item in the system.
 * This class is mapped to the 'item' table in the database.
 */
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name cannot be longer than 255 characters")
    private String name;

    @Size(max = 1000, message = "Description cannot be longer than 1000 characters")
    private String description;

    /**
     * Default constructor required by JPA.
     */
    public Item() {
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets the item's unique identifier.
     * @return the item ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the item's unique identifier.
     * @param id the item ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the item's name.
     * @return the item name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the item's name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the item's description.
     * @return the item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the item's description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
} 