package com.onlineLearningPlatform.Mapper;

import com.onlineLearningPlatform.dto.ResponseDto;
import com.onlineLearningPlatform.model.User;

public class ResponseMapper {

    public static ResponseDto maptoResponse(User user , ResponseDto responseDto){
        responseDto.setUsername(user.getName());
        responseDto.setEmailaddress(user.getEmailAddress());
        return  responseDto;
    }
}
