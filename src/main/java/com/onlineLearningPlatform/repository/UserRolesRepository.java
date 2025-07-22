package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
    UserRoles findByName(String newUserRole);
}
