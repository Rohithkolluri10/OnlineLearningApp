package com.onlineLearningPlatform.Service;

import com.onlineLearningPlatform.dto.CourseCreateDto;
import com.onlineLearningPlatform.dto.CourseDto;
import com.onlineLearningPlatform.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {
    List<CourseDto> getallCourses();

    CourseDto findById(Long id);

    CourseResponseDto createcourse(CourseCreateDto courseCreateDto);
}
