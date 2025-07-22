package com.onlineLearningPlatform.controllers;

import com.onlineLearningPlatform.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;


    public String getUserByID(){

    }

    public String updateUserProfile(){

    }

    public String getUserCourses(){

    }

    public String getUserNotifications(){

    }
}
