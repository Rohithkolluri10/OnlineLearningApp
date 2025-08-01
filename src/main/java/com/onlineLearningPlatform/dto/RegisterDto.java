package com.onlineLearningPlatform.dto;


import com.onlineLearningPlatform.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotBlank(message = "User name cannot be blank")
    private String Username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8,message = "Password should be Minimum 8 characters long")
    private String password;

    @NotBlank(message = "Email address cannot be Blank")
    @Email(message = "Use valid Email format")
    private String emailadress;


}
