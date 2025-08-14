package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.Course;
import org.hibernate.sql.exec.spi.JdbcParameters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllById(Long courseId);
}
