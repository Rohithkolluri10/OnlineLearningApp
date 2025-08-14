package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@EnableJpaRepositories
public interface LessonRepository extends JpaRepository<Lesson , Long> {

    Long countByCourseId(Long id);

    Optional<Integer> findTopByCourseOrderByLessonOrderDesc(Course course);

    @Query("SELECT COALESCE(MAX(l.lessonOrder), 0) + 1 FROM Lesson l WHERE l.course.id = :courseId")
    int findNextLessonOrderForCourse(@Param("courseId") Long courseId);

}
