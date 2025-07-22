package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRespository extends JpaRepository<Review, Long> {
}
