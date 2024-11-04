package com.qp.assessment.grocery.repository;

import com.qp.assessment.grocery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
