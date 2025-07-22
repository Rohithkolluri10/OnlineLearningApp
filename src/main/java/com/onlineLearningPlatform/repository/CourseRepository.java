package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.Course;
import org.hibernate.sql.exec.spi.JdbcParameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
