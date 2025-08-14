package com.onlineLearningPlatform.dto;

import com.onlineLearningPlatform.model.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseCreateDto {
    private String name;
    private String description;
    private double price;
    private String contents;
    private List<LessonDtoforCreateCourse> lessons;

}
