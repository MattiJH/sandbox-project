package com.example.itemservice.service;

import com.example.itemservice.model.Item;
import com.example.itemservice.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    private Item testItem;

    @BeforeEach
    void setUp() {
        testItem = new Item();
        testItem.setId(1L);
        testItem.setName("Test Item");
        testItem.setDescription("Test Description");
    }

    @Test
    void getAllItems_ShouldReturnAllItems() {
        // Arrange
        List<Item> items = Arrays.asList(testItem);
        when(itemRepository.findAll()).thenReturn(items);

        // Act
        List<Item> result = itemService.getAllItems();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testItem.getName(), result.get(0).getName());
        verify(itemRepository).findAll();
    }

    @Test
    void getItemById_WithValidId_ShouldReturnItem() {
        // Arrange
        when(itemRepository.findById(1L)).thenReturn(Optional.of(testItem));

        // Act
        Optional<Item> result = itemService.getItemById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testItem.getName(), result.get().getName());
        verify(itemRepository).findById(1L);
    }

    @Test
    void getItemById_WithInvalidId_ShouldReturnEmpty() {
        // Arrange
        when(itemRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<Item> result = itemService.getItemById(999L);

        // Assert
        assertFalse(result.isPresent());
        verify(itemRepository).findById(999L);
    }

    @Test
    void createItem_ShouldReturnCreatedItem() {
        // Arrange
        when(itemRepository.save(any(Item.class))).thenReturn(testItem);

        // Act
        Item result = itemService.createItem(testItem);

        // Assert
        assertNotNull(result);
        assertEquals(testItem.getName(), result.getName());
        verify(itemRepository).save(testItem);
    }

    @Test
    void updateItem_WithValidId_ShouldReturnUpdatedItem() {
        // Arrange
        when(itemRepository.findById(1L)).thenReturn(Optional.of(testItem));
        when(itemRepository.save(any(Item.class))).thenReturn(testItem);

        Item updateItem = new Item();
        updateItem.setName("Updated Name");
        updateItem.setDescription("Updated Description");

        // Act
        Optional<Item> result = itemService.updateItem(1L, updateItem);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(updateItem.getName(), result.get().getName());
        assertEquals(updateItem.getDescription(), result.get().getDescription());
        verify(itemRepository).findById(1L);
        verify(itemRepository).save(any(Item.class));
    }

    @Test
    void updateItem_WithInvalidId_ShouldReturnEmpty() {
        // Arrange
        when(itemRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<Item> result = itemService.updateItem(999L, testItem);

        // Assert
        assertFalse(result.isPresent());
        verify(itemRepository).findById(999L);
        verify(itemRepository, never()).save(any(Item.class));
    }

    @Test
    void deleteItem_WithValidId_ShouldReturnTrue() {
        // Arrange
        when(itemRepository.findById(1L)).thenReturn(Optional.of(testItem));

        // Act
        boolean result = itemService.deleteItem(1L);

        // Assert
        assertTrue(result);
        verify(itemRepository).findById(1L);
        verify(itemRepository).delete(testItem);
    }

    @Test
    void deleteItem_WithInvalidId_ShouldReturnFalse() {
        // Arrange
        when(itemRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        boolean result = itemService.deleteItem(999L);

        // Assert
        assertFalse(result);
        verify(itemRepository).findById(999L);
        verify(itemRepository, never()).delete(any(Item.class));
    }
} 