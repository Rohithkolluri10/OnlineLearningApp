package com.onlineLearningPlatform.Service;


import com.onlineLearningPlatform.dto.LessonDto;
import com.onlineLearningPlatform.dto.ResponseDto;

public interface LessonService {


    ResponseDto addLesson(LessonDto lessonDto);
}
