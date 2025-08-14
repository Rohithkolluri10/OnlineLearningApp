package com.onlineLearningPlatform.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonDto {

    private String title;

    private String description;

    private long courseId;

    private String videourl;
}
