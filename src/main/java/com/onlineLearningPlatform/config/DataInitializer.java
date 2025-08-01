package com.onlineLearningPlatform.config;

import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.model.UserRole;
import com.onlineLearningPlatform.repository.UserRepository;
import jakarta.persistence.Column;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmailAddress("kolluriAdmin@gmail.com").isEmpty()){
            User adminUser = new User();
            adminUser.setEmailAddress("kolluriAdmin@gmail.com");
            adminUser.setBlocked(false);
            adminUser.setName("Admin");
            adminUser.setActive(true);
            adminUser.setPassword(passwordEncoder.encode("Admin@123"));
            List<UserRole> adminRole = new ArrayList<>();
            adminRole.add(UserRole.ADMIN);
            adminUser.setRoles(adminRole);
            userRepository.save(adminUser);
            System.out.println("Admin User Has been Created");
        }else {
            System.out.println("Admin User Already exists");
        }

    }
}
