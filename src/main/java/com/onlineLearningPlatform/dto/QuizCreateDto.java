package com.onlineLearningPlatform.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizCreateDto {
    private Long id;
    private String name;
    private Set<QuestionDto> questionDtos;
}
