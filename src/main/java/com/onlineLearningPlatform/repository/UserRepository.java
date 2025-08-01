package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long
        > {
    User findByName(String name);
    User findByPassword(String password);

    Optional<User> findById(Long id);

    Optional<User> findByEmailAddress(String emailAddress);

    User save(Optional<User> user);
}
