package com.onlineLearningPlatform.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDto {
    private String answerText;
    private boolean isCorrect;
}
