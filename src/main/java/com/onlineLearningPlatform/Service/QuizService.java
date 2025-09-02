package com.onlineLearningPlatform.Service;

import com.onlineLearningPlatform.dto.QuizCreateDto;
import com.onlineLearningPlatform.dto.ResponseDto;
import org.springframework.stereotype.Service;

public interface QuizService {
    ResponseDto createQuiz(QuizCreateDto quizCreateDto);
}
