package com.onlineLearningPlatform.controllers;

import com.onlineLearningPlatform.Service.UserService;
import com.onlineLearningPlatform.dto.UserDto;
import com.onlineLearningPlatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin" , produces = {MediaType.APPLICATION_JSON_VALUE})
public class AdminController {

    @Autowired
    private UserDto userDto;

    @Autowired
    private UserService userService;

    @GetMapping("/fetch")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUser();

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PostMapping("/block")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> blockUser(@PathVariable Long Id){

        User user = userService.getUserById(Id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        userService.blockuser(user);

        return ResponseEntity.ok("User Sucessfully blocked");
    }

    @PostMapping("/updateRoles")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity updateUserRoles(@PathVariable String Username , String newRoleName){
        User user = userService.getUserbyUsername(Username);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        userService.updateUserRoles(user,newRoleName);
        return ResponseEntity.ok("ok");
    }

    //public String publishCourse(){



    //}

    //public String generateReports(){

    //}

}
