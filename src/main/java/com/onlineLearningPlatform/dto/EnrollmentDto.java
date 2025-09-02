package com.onlineLearningPlatform.dto;

import jdk.jfr.Name;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class EnrollmentDto {

    private String emailadress;

    private long courseId;

}
