package com.onlineLearningPlatform.controllers;

import com.onlineLearningPlatform.Mapper.UserMapper;
import com.onlineLearningPlatform.Service.UserService;
import com.onlineLearningPlatform.dto.RegisterDto;
import com.onlineLearningPlatform.dto.UserDto;
import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private UserMapper userMapper;


    @GetMapping("/getUser")
    public ResponseEntity<UserDto> getUserByID(@RequestParam long id){
        User user = userService.getUserById(id);
        UserDto userDto = userMapper.mapUsertoUserDTo(user, new UserDto());
        return ResponseEntity.status(HttpStatus.OK).body(
                userDto
        );
    }
    @PostMapping("/updateUserprofile")
    public ResponseEntity<?> updateUserProfile(@RequestBody RegisterDto registerDto){
        try{
            User updatedUser = userService.updateUserProfile(registerDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }

    @GetMapping("/getCoursesbyUser")
    public ResponseEntity<List<Course>> getUserCourses(@RequestParam String username) {
        List<Course> courseList = userService.getUserCourses(username);
        return ResponseEntity.status(HttpStatus.FOUND).body(courseList);

    }

    @GetMapping("/notifications")
    public String getUserNotifications(User user){
        userService.getNotificaton(user);

        return "okay";
    }
}
