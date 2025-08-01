package com.onlineLearningPlatform.Service.Impl;

import com.onlineLearningPlatform.Mapper.UserMapper;
import com.onlineLearningPlatform.Service.UserService;
import com.onlineLearningPlatform.dto.RegisterDto;
import com.onlineLearningPlatform.model.*;
import com.onlineLearningPlatform.repository.EnrollmentRespository;
import com.onlineLearningPlatform.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private EnrollmentRespository enrollmentRespository;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,EnrollmentRespository enrollmentRespository){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.enrollmentRespository=enrollmentRespository;
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();

    }

    public User getUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new RuntimeException("User not found");
        }else {
            return User.builder().build();
        }
    }

    @Override
    public void blockuser(Long Id) {
        User user = userRepository.findById(Id).orElseThrow(
                () ->    new UsernameNotFoundException("User not found with " + Id)
        );
        user.setBlocked(true);
        userRepository.save(user);
    }


    @Override
    public User updateUserRoles(String emailAdress, UserRole role) {
        User user = userRepository.findByEmailAddress(emailAdress).orElseThrow(
                () -> new UsernameNotFoundException("User not found with following Email address" + emailAdress)
        );
        List<UserRole> roles = new ArrayList<>();
        roles.add(role);
        if (user.getRoles().contains(UserRole.ADMIN)){
            throw new RuntimeException("User already has the highest role (Admin).");
        }if (user.getRoles().contains(UserRole.INSTRUCTOR)){
            user.setRoles(List.of(UserRole.ADMIN));
            userRepository.save(user);
        }else {
            user.setRoles(roles);
            userRepository.save(user);
        }
        return user;
    }


    @Override
    public User getUserbyUsername(String username) {
        User user = userRepository.findByName(username);
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
        newuser.setPassword(encodedePassword);
        List<UserRole> defaultrole = new ArrayList<>();
        defaultrole.add(UserRole.STUDENT);
        newuser.setRoles(defaultrole);
        newuser.setBlocked(false);
        newuser.setActive(true);
        UserMapper.mapToUser(registerDto, newuser);
        userRepository.save(newuser);
        return newuser;

    }

    @Override
    public User updateUserProfile(RegisterDto registerDto) {
        User existinguser = userRepository.findByEmailAddress(registerDto.getEmailadress()).orElseThrow(
                () -> new RuntimeException("User not found with following details:" +  registerDto.getEmailadress()));
        existinguser.setName(registerDto.getUsername());
        existinguser.setEmailAddress(registerDto.getEmailadress());
        if (registerDto.getPassword() != null && !registerDto.getPassword().isEmpty()) {
            String encodedpassword = passwordEncoder.encode(registerDto.getPassword());
            existinguser.setPassword(encodedpassword);
        }
        return userRepository.save(existinguser);


    }

    @Override
    public List<Course> getUserCourses(String username) {
        User user = userRepository.findByName(username);
        List<Enrollment> enrollments = enrollmentRespository.findByUser(user);
        List<Course> courseslist = new ArrayList<>();
        for ( Enrollment enrollment : enrollments){
            Course courses = enrollment.getCourse();
        }
        return courseslist;



    }

    @Override
    public void getNotificaton(User user) {

    }
}

