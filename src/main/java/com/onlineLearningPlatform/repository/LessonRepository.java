package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson , Long> {

    Optional<Integer> findTopByCourseOrderByLessonOrderDesc(Course course);

}
