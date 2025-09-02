package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllById(Long courseId);

    List<Course> findByDescriptionContainingIgnoreCase(String keyword);
}
