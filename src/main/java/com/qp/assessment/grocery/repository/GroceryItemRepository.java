package com.qp.assessment.grocery.repository;

import com.qp.assessment.grocery.model.GroceryItem;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {

    Optional<GroceryItem> findByName(String name);

    List<GroceryItem> findAll(Specification specification);
}