package com.onlineLearningPlatform.Service;

import com.onlineLearningPlatform.dto.*;
import com.onlineLearningPlatform.model.Review;

import java.util.List;

public interface CourseService {
    List<CourseDto> getallCourses();

    CourseDto findById(Long id);

    CourseResponseDto createcourse(CourseCreateDto courseCreateDto);

    ResponseDto updateCourse(CourseDto courseDto);

    ResponseDto deleteCourse(Long id);

    List<CourseDto> searchCourse(String keyword);

    ResponseDto addreviewtoCourse(ReviewDto reviewDto);

    ResponseDto getReview(Long id);
}
