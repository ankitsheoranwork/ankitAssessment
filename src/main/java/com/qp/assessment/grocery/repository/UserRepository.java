package com.qp.assessment.grocery.repository;

import com.qp.assessment.grocery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
