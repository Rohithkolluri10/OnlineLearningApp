package com.onlineLearningPlatform.Service;


import com.onlineLearningPlatform.dto.EnrollmentDto;
import com.onlineLearningPlatform.dto.ResponseDto;

public interface EnrollmentService {
    ResponseDto enrolluser(EnrollmentDto enrollmentDto);
}
