package com.onlineLearningPlatform.dto;


import com.onlineLearningPlatform.model.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterDto {

    @NotBlank(message = "User name cannot be blank")
    private String Username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8,message = "Password should be Minimum 8 characters long")
    private String password;

    @NotBlank(message = "Email address cannot be Blank")
    @Email(message = "Use valid Email format")
    private String emailadress;


    private List<UserRoles> role;
}
