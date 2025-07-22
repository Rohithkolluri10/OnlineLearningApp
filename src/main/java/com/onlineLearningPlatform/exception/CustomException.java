package com.onlineLearningPlatform.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException{
    private String errorcode;
    private String errorMessage;

    public CustomException(String message){
        super(message);
    }

    public CustomException(String message, Throwable cause){
        super(message, cause);
    }

    public CustomException(String errorcode, String errorMessage){
        super(errorMessage);
        this.errorcode=errorcode;
        this.errorMessage=errorMessage;

    }
}
