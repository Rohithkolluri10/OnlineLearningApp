package com.onlineLearningPlatform.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponseDto extends ResponseDto{


    private String accessToken;

    public AuthResponseDto(String accessToken, String message){
        super(accessToken != null , message );
        this.accessToken = accessToken;
    }

}
