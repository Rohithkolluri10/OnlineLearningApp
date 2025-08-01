package com.onlineLearningPlatform.controllers;

import com.onlineLearningPlatform.Mapper.ResponseMapper;
import com.onlineLearningPlatform.Mapper.UserMapper;
import com.onlineLearningPlatform.Service.UserService;
import com.onlineLearningPlatform.config.JwtFilter;
import com.onlineLearningPlatform.dto.*;
import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.utils.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Tag(
        name = "REST APIs for OnlineLearning for Authentication",
        description = "End Points to Register,Login, Logout"
)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Operation(
            summary = "Register User REST API",
            description = "REST API to Register User"
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

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid RegisterDto registerDto){

        User user = userService.registeruser(registerDto);
        UserDto userDto= ResponseMapper.maptoResponse(user,new UserDto());

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }@Operation(
            summary = "Login User REST API",
            description = "Rest Api to Login User"
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


    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginDto loginDto){

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmailAddress(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenProvider.generateJwtToken(authentication);

            return ResponseEntity.ok(new AuthResponseDto(jwt,"Login Sucessfull"));
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AuthResponseDto(null,"Invalid Email or Password"));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new AuthResponseDto(null, "A Unexected Error has occured")
            );
        }


    }
    @Operation(
            summary = "Logout REST API",
            description = "End point to Logout User"
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

    @GetMapping("/logout")
    public ResponseEntity<AuthResponseDto> logoutUser(){
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new AuthResponseDto("true","Logout Sucessful"));
    }

}
