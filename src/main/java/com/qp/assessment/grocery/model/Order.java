package com.qp.assessment.grocery.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items;

    private Double totalPrice;

    @Data
    @Embeddable
    public static class OrderItem {
        private String name;
        private Integer quantity;
        private Double price;
    }
}
