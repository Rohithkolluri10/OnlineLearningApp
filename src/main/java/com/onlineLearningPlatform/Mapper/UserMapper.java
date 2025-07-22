package com.onlineLearningPlatform.Mapper;

import com.onlineLearningPlatform.dto.RegisterDto;
import com.onlineLearningPlatform.dto.UserDto;
import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.model.UserRoles;

import java.util.List;
import java.util.Set;

public class UserMapper {

    public static RegisterDto mapToRegisterdto(User user, RegisterDto registerDto){
        registerDto.setUsername(user.getName());
        registerDto.setEmailadress(user.getEmailAddress());
        registerDto.setPassword(user.getPassword());
        registerDto.setRole((List<UserRoles>) user.getRoles());
        return registerDto;
    }

    public static User mapToUser(RegisterDto registerDto, User user){
        user.setName(registerDto.getUsername());
        user.setEmailAddress(user.getEmailAddress());
        user.setPassword(registerDto.getPassword());
        user.setRoles((Set<UserRoles>) registerDto.getRole());
        return user;
    }

}
