package com.onlineLearningPlatform.controllers;

import com.onlineLearningPlatform.Service.EnrollmentService;
import com.onlineLearningPlatform.dto.EnrollmentDto;
import com.onlineLearningPlatform.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@Tag(
        name = "REST APIs for OnlineLearning for Student Enrollment",
        description = "Rest API for Student Enrollment"
)
@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Operation(
            summary = "Register for the Course",
            description = "Register Using CourseId and the UserID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @PostMapping("/enroll")
    public ResponseEntity<ResponseDto> studentEnrollment(@RequestBody EnrollmentDto enrollmentDto){

        ResponseDto responseDto = enrollmentService.enrolluser(enrollmentDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                responseDto
        );
    }

}
