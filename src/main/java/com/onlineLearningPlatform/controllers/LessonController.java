package com.onlineLearningPlatform.controllers;

import com.onlineLearningPlatform.Service.Impl.S3fileuploader;
import com.onlineLearningPlatform.Service.LessonService;
import com.onlineLearningPlatform.dto.LessonDto;
import com.onlineLearningPlatform.dto.NewLessonAddingDto;
import com.onlineLearningPlatform.dto.ResponseDto;
import com.onlineLearningPlatform.model.Lesson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(
        name = "REST APIs for OnlineLearning for Lessons for Instructor",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE"
)

@Slf4j
@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final S3fileuploader s3fileuploader;
    private final LessonService lessonService;

    public LessonController(S3fileuploader s3fileuploader, LessonService lessonService) {
        this.s3fileuploader = s3fileuploader;
        this.lessonService = lessonService;
    }
    @Operation(
            summary = "Lesson Upload for Instructor",
            description = "Uploads the files to s3 bucket"
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

    @PostMapping("/lessonupload")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<ResponseDto> uploadlessonVideo(
            @RequestParam ("file")MultipartFile file,
            @ModelAttribute NewLessonAddingDto newLessonAddingDto
            ) throws IOException {

        String videoUrl = s3fileuploader.uploadfile(file);
        log.info("Video Uploaded to S3",videoUrl);

        LessonDto lessonDto = new LessonDto();
        lessonDto.setCourseId(newLessonAddingDto.getCourseId());
        lessonDto.setDescription(newLessonAddingDto.getDescription());
        lessonDto.setTitle(newLessonAddingDto.getTitle());
        lessonDto.setVideourl(videoUrl);

        ResponseDto responseDto = lessonService.addLesson(lessonDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                responseDto
        );


    }
}
