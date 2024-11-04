package com.qp.assessment.grocery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Table(name = "grocery_items", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data
public class GroceryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private Double price;

    private Integer quantity;

    @NotBlank(message = "Category cannot be null or empty")
    private String category;

    private String description;
}
