package com.onlineLearningPlatform.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {

    private long courseId;

    private String commnet;
}
