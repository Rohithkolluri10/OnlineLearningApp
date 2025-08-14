package com.onlineLearningPlatform.controllers;

import com.onlineLearningPlatform.Mapper.UserMapper;
import com.onlineLearningPlatform.Service.UserService;
import com.onlineLearningPlatform.dto.ResponselistDto;
import com.onlineLearningPlatform.dto.RoleResponseDto;
import com.onlineLearningPlatform.dto.UserDto;
import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.model.UserRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "REST APIs for OnlineLearning for Admin Roles",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE"
)
@RestController
@RequestMapping(path = "/api/admin" , produces = {MediaType.APPLICATION_JSON_VALUE})
public class AdminController {

    private UserDto userDto;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Fetch All Users REST API",
            description = "REST API to Fetch All the User from Admin"
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
    @GetMapping("/fetch")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<ResponselistDto>> getAllUsers() {
        List<User> users = userService.getAllUser();
        List<ResponselistDto> userDtoListA = userMapper.mapUserlisttoUserDTo(users);

        return new ResponseEntity<>(userDtoListA, HttpStatus.OK);
    }
    @Operation(
            summary = "Block Users REST API",
            description = "REST API to block the User from Admin"
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
    @PostMapping("/block")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> blockUser(@RequestParam Long Id) {
        userService.blockuser(Id);
        return ResponseEntity.ok("User Sucessfully blocked");
    }
    @Operation(
            summary = "Update the Role  REST API",
            description = "REST API to Update  User Role from Admin"
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
    @PostMapping("/updateRoles")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<RoleResponseDto> updateUserRoles(@RequestParam String emailAddress, UserRole role) {
        User user = userService.updateUserRoles(emailAddress, role);
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setUsername(user.getEmailAddress());
        roleResponseDto.setRole(user.getRoles());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                roleResponseDto
        );
    }


}