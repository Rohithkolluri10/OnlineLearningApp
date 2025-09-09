package com.onlineLearningPlatform.controllers;


import com.onlineLearningPlatform.Service.QuizService;
import com.onlineLearningPlatform.dto.QuizCreateDto;
import com.onlineLearningPlatform.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST APIs for OnlineLearning for Quiz",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE"
)
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    public void getQuizesByCourse(){

    }

    public void getQuizByID(){

    }
    @Operation(
            summary = "Create Quiz for Instructor",
            description = "Create Quiz Using CourseId and the Questions and Answers"
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
    @PostMapping("/createQuiz")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<ResponseDto> createQuiz(@RequestBody QuizCreateDto quizCreateDto){
        ResponseDto responseDto = quizService.createQuiz(quizCreateDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(
                responseDto
        );

    }

    public ResponseEntity<String> updateQuiz(){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "Testing the Pull request"
        );

    }

    public void deleteQuiz(){

    }

    public void submitQuiz(){

    }

    public void attemptQuiz(){

    }

    public void getLeaderBoardScores(){

    }


}
