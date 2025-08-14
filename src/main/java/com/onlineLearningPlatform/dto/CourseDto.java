package com.onlineLearningPlatform.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    private Long Id;

    private String name;

    private String description;

    private String contents;
}
