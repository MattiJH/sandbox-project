package com.example.itemservice.controller;

import com.example.itemservice.model.Item;
import com.example.itemservice.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    private Item testItem;

    @BeforeEach
    void setUp() {
        testItem = new Item();
        testItem.setId(1L);
        testItem.setName("Test Item");
        testItem.setDescription("Test Description");
    }

    @Test
    void getAllItems_ShouldReturnItems() throws Exception {
        when(itemService.getAllItems()).thenReturn(Arrays.asList(testItem));

        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(testItem.getId()))
                .andExpect(jsonPath("$[0].name").value(testItem.getName()))
                .andExpect(jsonPath("$[0].description").value(testItem.getDescription()));
    }

    @Test
    void getItem_WithValidId_ShouldReturnItem() throws Exception {
        when(itemService.getItemById(1L)).thenReturn(Optional.of(testItem));

        mockMvc.perform(get("/api/items/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testItem.getId()))
                .andExpect(jsonPath("$.name").value(testItem.getName()))
                .andExpect(jsonPath("$.description").value(testItem.getDescription()));
    }

    @Test
    void getItem_WithInvalidId_ShouldReturnNotFound() throws Exception {
        when(itemService.getItemById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/items/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createItem_ShouldReturnCreatedItem() throws Exception {
        when(itemService.createItem(any(Item.class))).thenReturn(testItem);

        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testItem)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testItem.getId()))
                .andExpect(jsonPath("$.name").value(testItem.getName()))
                .andExpect(jsonPath("$.description").value(testItem.getDescription()));
    }

    @Test
    void updateItem_WithValidId_ShouldReturnUpdatedItem() throws Exception {
        when(itemService.updateItem(eq(1L), any(Item.class))).thenReturn(Optional.of(testItem));

        mockMvc.perform(put("/api/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testItem)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testItem.getId()))
                .andExpect(jsonPath("$.name").value(testItem.getName()))
                .andExpect(jsonPath("$.description").value(testItem.getDescription()));
    }

    @Test
    void updateItem_WithInvalidId_ShouldReturnNotFound() throws Exception {
        when(itemService.updateItem(eq(999L), any(Item.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/items/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testItem)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteItem_WithValidId_ShouldReturnNoContent() throws Exception {
        when(itemService.deleteItem(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/items/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteItem_WithInvalidId_ShouldReturnNotFound() throws Exception {
        when(itemService.deleteItem(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/items/999"))
                .andExpect(status().isNotFound());
    }
} 