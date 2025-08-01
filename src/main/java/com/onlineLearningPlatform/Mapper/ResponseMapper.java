package com.onlineLearningPlatform.Mapper;

import com.onlineLearningPlatform.Constants.AppConstants;
import com.onlineLearningPlatform.dto.ResponseDto;
import com.onlineLearningPlatform.dto.UserDto;
import com.onlineLearningPlatform.model.User;

public class ResponseMapper {

    public static UserDto maptoResponse(User user , UserDto userDto){
        userDto.setUsername(user.getName());
        userDto.setEmail(user.getEmailAddress());
        userDto.setApiresponse(AppConstants.REGISTER_201);
        userDto.setApiStatus(AppConstants.STATUS_200);
        return  userDto;
    }
}
