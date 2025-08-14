package com.onlineLearningPlatform.Mapper;

import com.onlineLearningPlatform.dto.RegisterDto;
import com.onlineLearningPlatform.dto.ResponselistDto;
import com.onlineLearningPlatform.dto.UserDto;
import com.onlineLearningPlatform.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {

    public static RegisterDto mapToRegisterdto(User user, RegisterDto registerDto){
        registerDto.setUsername(user.getName());
        registerDto.setEmailadress(user.getEmailAddress());
        registerDto.setPassword(user.getPassword());
        return registerDto;
    }

    public static User mapToUser(RegisterDto registerDto, User user){
        user.setName(registerDto.getUsername());
        user.setEmailAddress(registerDto.getEmailadress());
        return user;
    }

    public static UserDto mapUsertoUserDTo(Optional<User> user , UserDto userDto){
        userDto.setUsername(user.get().getName());
        userDto.setEmail(user.get().getEmailAddress());
        userDto.setActive(user.get().isActive());
        userDto.setBlocked(user.get().isBlocked());
        return userDto;
    }


    public List<ResponselistDto> mapUserlisttoUserDTo(List<User> users) {
        List<ResponselistDto> userDtoList = new ArrayList<>();
        for (User user:users ){
            ResponselistDto currentUserDto = new ResponselistDto();
            currentUserDto.setUsername(user.getName());
            currentUserDto.setEmailAdress(user.getEmailAddress());
            currentUserDto.setBlocked(user.isBlocked());
            currentUserDto.setActive(user.isActive());
            userDtoList.add(currentUserDto);
        }
        return userDtoList;
    }
}
