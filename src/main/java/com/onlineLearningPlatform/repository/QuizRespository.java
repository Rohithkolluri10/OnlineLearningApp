package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRespository extends JpaRepository<Quiz, Long> {
}
