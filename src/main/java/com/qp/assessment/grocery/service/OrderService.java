package com.qp.assessment.grocery.service;

import com.qp.assessment.grocery.errorhandling.InsufficientQuantityException;
import com.qp.assessment.grocery.model.GroceryItem;
import com.qp.assessment.grocery.model.Order;
import com.qp.assessment.grocery.repository.GroceryItemRepository;
import com.qp.assessment.grocery.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Transactional
    @CacheEvict(value = {"groceryItems","orders"}, allEntries = true)
    public Order createOrder(Order order) {
        List<Order.OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;
        for (Order.OrderItem orderItem : order.getItems()) {
            GroceryItem groceryItem = groceryItemRepository.findByName(orderItem.getName())
                    .orElseThrow(() -> new RuntimeException("Item not found: " + orderItem.getName()));

            if (orderItem.getQuantity() > groceryItem.getQuantity()) {
                throw new InsufficientQuantityException("Insufficient quantity for item: " + groceryItem.getName());
            }

            orderItem.setPrice(groceryItem.getPrice());
            totalPrice += groceryItem.getPrice() * orderItem.getQuantity();
            orderItems.add(orderItem);
            groceryItem.setQuantity(groceryItem.getQuantity() - orderItem.getQuantity());
            groceryItemRepository.save(groceryItem);
        }

        order.setTotalPrice(totalPrice);
        order.setItems(orderItems);
        return orderRepository.save(order);
    }

    @Cacheable(value = "orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
