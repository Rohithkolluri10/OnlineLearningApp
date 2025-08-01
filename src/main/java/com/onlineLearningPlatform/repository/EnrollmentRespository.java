package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.Enrollment;
import com.onlineLearningPlatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRespository extends JpaRepository<Enrollment, Long> {
    @Override
    Optional<Enrollment> findById(Long id);

    List<Enrollment> findByUser(User user);
}
