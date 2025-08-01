package com.onlineLearningPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponselistDto {
    private String username;
    private String emailAdress;
    private boolean isActive;
    private boolean isBlocked;
}
