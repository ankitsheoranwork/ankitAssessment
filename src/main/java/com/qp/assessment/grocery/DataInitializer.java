package com.qp.assessment.grocery;

import com.qp.assessment.grocery.model.Role;
import com.qp.assessment.grocery.model.User;
import com.qp.assessment.grocery.repository.RoleRepository;
import com.qp.assessment.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("USER");
        roleRepository.save(userRole);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("$2a$10$D9jG4U1lB9/17.tMY9R8UOIvaJR7oRi7CWuTLdOGVEn6jfTqONWTS");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        admin.setRoles(adminRoles);
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword("$2a$10$D9jG4U1lB9/17.tMY9R8UOTxKDlSOGHzyFGi8CkLapd5W3EllGwVm");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRoles(userRoles);
        userRepository.save(user);
    }
}
