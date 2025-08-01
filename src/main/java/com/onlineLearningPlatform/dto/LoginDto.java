package com.onlineLearningPlatform.dto;

import jakarta.validation.MessageInterpolator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDto {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String emailAddress;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
