package com.onlineLearningPlatform.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseResponseDto {
    private Long Id;
    private String name;
    private double price;
    private Long numofDefaultlessonadded;
}
