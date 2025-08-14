package com.onlineLearningPlatform.Mapper;

import com.onlineLearningPlatform.dto.CourseDto;
import com.onlineLearningPlatform.dto.CourseResponseDto;
import com.onlineLearningPlatform.model.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {

    public static List<CourseDto> mapCourselisttoCourseDtolist(List<Course> courses) {
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(course.getId());
            courseDto.setName(course.getName());
            courseDto.setDescription(course.getDescription());
            courseDto.setContents(course.getContents());
            courseDtos.add(courseDto);
        }
        return courseDtos;
    }

    public static CourseDto mapcoursetocourseDto(Course course, CourseDto courseDto) {
        courseDto.setId(course.getId());
        courseDto.setContents(course.getContents());
        courseDto.setDescription(course.getDescription());
        courseDto.setName(course.getName());

        return courseDto;
    }
}
