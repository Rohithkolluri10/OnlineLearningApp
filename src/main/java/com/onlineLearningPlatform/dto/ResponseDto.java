package com.onlineLearningPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {

    public ResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean success;

    public String message;

}
