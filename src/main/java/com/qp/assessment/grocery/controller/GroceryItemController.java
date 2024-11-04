package com.qp.assessment.grocery.controller;

import com.qp.assessment.grocery.model.GroceryItem;
import com.qp.assessment.grocery.service.GroceryItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grocery-items")
@Validated
public class GroceryItemController {

    @Autowired
    private GroceryItemService groceryItemService;

    @PostMapping
    public ResponseEntity<GroceryItem> addGroceryItem(@Valid @RequestBody GroceryItem groceryItem) {
        GroceryItem createdItem = groceryItemService.addGroceryItem(groceryItem);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) Double minPrice,
    @RequestParam(required = false) Double maxPrice,
    @RequestParam(required = false) String category){
        List<GroceryItem> items = groceryItemService.getAllGroceryItems(name, minPrice, maxPrice, category);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long id, @Valid @RequestBody GroceryItem groceryItem) {
        GroceryItem updatedItem = groceryItemService.updateGroceryItem(id, groceryItem);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id) {
        groceryItemService.deleteGroceryItem(id);
        return ResponseEntity.noContent().build();
    }
}
