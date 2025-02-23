package com.example.itemservice.service;

import com.example.itemservice.model.Item;
import com.example.itemservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that handles business logic for Item operations.
 * This class serves as an intermediary layer between the controller and repository.
 */
@Service
public class ItemService {
    
    private final ItemRepository itemRepository;

    /**
     * Constructs an ItemService with the required repository.
     * @param itemRepository the repository to use for data access
     */
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Retrieves all items from the database.
     * @return a list of all items
     */
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Retrieves a specific item by its ID.
     * @param id the ID of the item to retrieve
     * @return an Optional containing the found item, or empty if not found
     */
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    /**
     * Creates a new item in the database.
     * @param item the item to create
     * @return the created item with its generated ID
     */
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    /**
     * Updates an existing item in the database.
     * @param id the ID of the item to update
     * @param itemDetails the updated item details
     * @return an Optional containing the updated item, or empty if the item was not found
     */
    public Optional<Item> updateItem(Long id, Item itemDetails) {
        return itemRepository.findById(id)
            .map(existingItem -> {
                existingItem.setName(itemDetails.getName());
                existingItem.setDescription(itemDetails.getDescription());
                return itemRepository.save(existingItem);
            });
    }

    /**
     * Deletes an item from the database.
     * @param id the ID of the item to delete
     * @return true if the item was deleted, false if the item was not found
     */
    public boolean deleteItem(Long id) {
        return itemRepository.findById(id)
            .map(item -> {
                itemRepository.delete(item);
                return true;
            })
            .orElse(false);
    }
} 