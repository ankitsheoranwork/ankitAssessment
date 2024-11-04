package com.qp.assessment.grocery.service;

import com.qp.assessment.grocery.model.GroceryItem;
import com.qp.assessment.grocery.repository.GroceryItemRepository;
import com.qp.assessment.grocery.repository.specification.GroceryItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @CacheEvict(value = "groceryItems", allEntries = true)
    public GroceryItem addGroceryItem(GroceryItem groceryItem) {
        Optional<GroceryItem> existingItemOpt = groceryItemRepository.findByName(groceryItem.getName());

        if (existingItemOpt.isPresent()) {
            GroceryItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + groceryItem.getQuantity());
            updateExistingDetails(groceryItem,existingItem);
            return groceryItemRepository.save(existingItem);
        } else {
            return groceryItemRepository.save(groceryItem);
        }
    }

    private void updateExistingDetails(GroceryItem groceryItem, GroceryItem existingItem) {
        if (null != groceryItem.getPrice()) {
            existingItem.setPrice(groceryItem.getPrice());
        }
        if(null!= groceryItem.getDescription()) {
            existingItem.setDescription(groceryItem.getDescription());
        }
        if(null!= groceryItem.getCategory()) {
            existingItem.setCategory(groceryItem.getCategory());
        }
    }

    @Cacheable(value = "groceryItems")
    public List<GroceryItem> getAllGroceryItems(String name, Double minPrice, Double maxPrice, String category) {
            Specification<GroceryItem> spec = Specification.where(GroceryItemSpecification.filterByName(name))
                    .and(GroceryItemSpecification.filterByPriceRange(minPrice, maxPrice))
                    .and(GroceryItemSpecification.filterByCategory(category));
            return groceryItemRepository.findAll(spec);
    }

    @CacheEvict(value = "groceryItems", allEntries = true)
    public GroceryItem updateGroceryItem(Long id, GroceryItem item) {
        item.setId(id);
        return groceryItemRepository.save(item);
    }

    @CacheEvict(value = "groceryItems", allEntries = true)
    public void deleteGroceryItem(Long id) {
        groceryItemRepository.deleteById(id);
    }
}
