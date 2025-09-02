package com.onlineLearningPlatform.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class QuestionDto {
    private String ques;
    private Set<AnswerDto> answerDtos;
}
