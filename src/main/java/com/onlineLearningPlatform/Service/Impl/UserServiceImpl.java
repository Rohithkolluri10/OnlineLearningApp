package com.onlineLearningPlatform.Service.Impl;

import com.onlineLearningPlatform.Mapper.UserMapper;
import com.onlineLearningPlatform.Service.UserService;
import com.onlineLearningPlatform.dto.RegisterDto;
import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.model.UserPrincipal;
import com.onlineLearningPlatform.model.UserRoles;
import com.onlineLearningPlatform.repository.UserRepository;
import com.onlineLearningPlatform.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();

    }

    public User getUserById(Long Id){
        return userRepository.findBy(Id);
    }

    @Override
    public void blockuser(User user) {
        user.setBlocked(true);
        userRepository.save(user);

    }

    @Override
    public void updateUserRoles(User user, String newUserRole) {
        UserRoles newroles = userRolesRepository.findByName(newUserRole);
        List<UserRoles> roles = new ArrayList<>();
        roles.add(newroles);
        user.setRoles(roles);
        userRepository.save(user);

        }


    @Override
    public User getUserbyUsername(String username) {
        User user = userRepository.findByUserName(username);
        return user;
    }


    @Override
    public Optional<User> getUserbyEmail(String emailadress) {
        Optional<User> user = userRepository.findByEmailAddress(emailadress);
        if (user.isEmpty()){
            userRepository.save(user);
        } else throw new RuntimeException("User Already Exists");
        return user;
    }

    @Override
    public User registeruser(RegisterDto registerDto) {
        if (userRepository.findByEmailAddress(registerDto.getEmailadress()).isPresent()){
            throw new RuntimeException("User Already Exists with the mentioned Email Id");
        }
        String encodedePassword = passwordEncoder.encode(registerDto.getPassword());
        User newuser = new User();
        UserMapper.mapToUser(registerDto, newuser);
        userRepository.save(newuser);
        return newuser;

    }

    @Override
    public UserPrincipal getUserbyName(String username) {
        User user = userRepository.findByUserName(username);
        return null;
    }
}

