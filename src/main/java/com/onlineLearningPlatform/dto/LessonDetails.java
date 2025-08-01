package com.onlineLearningPlatform.dto;

import com.onlineLearningPlatform.model.Course;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonDetails {

    private Long Id;

    private String title;

    private String description;

    private Course course;

    private String videoUrl;

    private int order;

}
