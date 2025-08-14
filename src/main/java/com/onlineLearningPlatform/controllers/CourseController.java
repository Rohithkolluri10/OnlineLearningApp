package com.onlineLearningPlatform.controllers;

import com.onlineLearningPlatform.Service.CourseService;
import com.onlineLearningPlatform.Service.LessonService;
import com.onlineLearningPlatform.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "REST APIs for OnlineLearning for Courses",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE"
)
@RestController
@RequestMapping("api/Courses")
public class CourseController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseService courseService;


    @Operation(
            summary = "Fetch All Courses REST API",
            description = "REST API to Fetch All the Courses"
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
    @GetMapping("/getAllCourses")
    public List<CourseDto> getAllCourses(){
        List<CourseDto> courseDtos = courseService.getallCourses();
        return courseDtos;
    }

    @Operation(
            summary = "Fetch Course By ID REST API",
            description = "REST API to Fetch All the Courses"
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
    @GetMapping("/getcoursebyid")
    public ResponseEntity<CourseDto> getCourseById(Long Id){
        CourseDto courseDto = courseService.findById(Id);
        return ResponseEntity.status(HttpStatus.OK).body(
                courseDto
        );
    }

    @Operation(
            summary = "Fetch to create Course REST API",
            description = "REST API to Create Course for Instructor"
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
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/createcourse")
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseCreateDto courseCreateDto){
       CourseResponseDto courseResponseDto = courseService
               .createcourse(courseCreateDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(
               courseResponseDto
       );
    }

    public void updateCourse(){

    }

    public void deleteCourse(){

    }

    public void searchCourses(){

    }

    public void getCourseReview(){

    }

    public void addReviewToCourse(){


    }

    public void enrollUserToCourse(){

    }

    @Operation(
            summary = "Add new Lesson for Existing Course",
            description = "REST API to Add Lesson to the Course"
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
    @PostMapping("/addnewlesson")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<ResponseDto> addnewLesson(@RequestBody LessonDto lessonDto){
        lessonService.addLesson(lessonDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                new ResponseDto(true,"Success")
        );

    }

}
