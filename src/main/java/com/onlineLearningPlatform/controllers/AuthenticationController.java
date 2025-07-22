package com.onlineLearningPlatform.controllers;

import com.onlineLearningPlatform.Mapper.ResponseMapper;
import com.onlineLearningPlatform.Mapper.UserMapper;
import com.onlineLearningPlatform.Service.UserService;
import com.onlineLearningPlatform.dto.RegisterDto;
import com.onlineLearningPlatform.dto.ResponseDto;
import com.onlineLearningPlatform.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public class AuthenticationController {

    @Autowired
    private UserService userService;

    public ResponseEntity<ResponseDto> registerUser(@RequestBody @Valid RegisterDto registerDto, BindingResult result){
        if (result.hasErrors()){
            throw new RuntimeException("User has Errors");
        }
        User user = userService.registeruser(registerDto);
        ResponseDto responseDto = ResponseMapper.maptoResponse(user,new ResponseDto());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public ResponseEntity<AuthResonseDto> loginUser(){

    }

    public ResponseEntity<AuthResonseDto> logoutUser(){

    }
}
