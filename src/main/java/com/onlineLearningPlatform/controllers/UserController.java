package com.onlineLearningPlatform.controllers;

import com.onlineLearningPlatform.Mapper.UserMapper;
import com.onlineLearningPlatform.Service.UserService;
import com.onlineLearningPlatform.dto.RegisterDto;
import com.onlineLearningPlatform.dto.UserDto;
import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(
        name = "REST APIs for OnlineLearning for Users",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE"
)
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Operation(
            summary = "Fetch Individual User by ID",
            description = "REST API to Fetch All the User"
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
    @GetMapping("/getUser")
    public ResponseEntity<UserDto> getUserByID(@RequestParam long id){
        Optional<User> user = userService.getUserById(id);
        UserDto userDto = userMapper.mapUsertoUserDTo(user, new UserDto());
        return ResponseEntity.status(HttpStatus.OK).body(
                userDto
        );
    }
    @Operation(
            summary = "Update the User profile REST API",
            description = "REST API to Update the User Profile"
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
    @PostMapping("/updateUserprofile")
    public ResponseEntity<?> updateUserProfile(@RequestBody RegisterDto registerDto){
        try{
            User updatedUser = userService.updateUserProfile(registerDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }
    @Operation(
            summary = "Get All the courses by User REST API",
            description = "REST API to Fetch All the User courses"
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

    @GetMapping("/getCoursesbyUser")
    public ResponseEntity<List<Course>> getUserCourses(@RequestParam String username) {
        List<Course> courseList = userService.getUserCourses(username);
        return ResponseEntity.status(HttpStatus.FOUND).body(courseList);

    }
    @Operation(
            summary = "Get the pending Notification REST API",
            description = "REST API to Fetch All notification"
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
    @GetMapping("/notifications")
    public String getUserNotifications(User user){
        userService.getNotificaton(user);

        return "okay";
    }
}
