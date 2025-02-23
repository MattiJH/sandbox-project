package com.example.itemservice.controller;

import com.example.itemservice.model.Item;
import com.example.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for handling Item-related HTTP requests.
 * Provides endpoints for CRUD operations on Items.
 */
@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemController {

    private final ItemService itemService;

    /**
     * Constructs an ItemController with the required service.
     * @param itemService the service to handle business logic
     */
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Retrieves all items.
     * @return ResponseEntity containing list of all items with HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    /**
     * Retrieves a specific item by its ID.
     * @param id the ID of the item to retrieve
     * @return ResponseEntity containing the item if found (200 OK) or 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(item -> ResponseEntity.ok().body(item))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new item.
     * @param item the item to create
     * @return ResponseEntity containing the created item with HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    /**
     * Updates an existing item.
     * @param id the ID of the item to update
     * @param item the updated item details
     * @return ResponseEntity containing the updated item (200 OK) or 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item)
                .map(updatedItem -> ResponseEntity.ok().body(updatedItem))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes an item.
     * @param id the ID of the item to delete
     * @return ResponseEntity with HTTP status 204 (No Content) or 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
} 