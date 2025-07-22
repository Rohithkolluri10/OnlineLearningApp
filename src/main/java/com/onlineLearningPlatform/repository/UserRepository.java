package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long
        > {
    User findByUserName(String username);
    User findByPassword(String password);

    User findBy(Long Id);

    Optional<User> findByEmailAddress(String emailAddress);

    void save(Optional<User> user);
}
