package com.onlineLearningPlatform.Service;

import com.onlineLearningPlatform.dto.RegisterDto;
import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.model.UserRole;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();

    Optional<User> getUserById(Long id);

    void blockuser(Long user1);

    User updateUserRoles(String user, UserRole role);

    User getUserbyUsername(String username);

    Optional<User> getUserbyEmail(String emailadress);

    User registeruser(@Valid RegisterDto registerDto);

    User updateUserProfile(RegisterDto registerDto);

    List<Course> getUserCourses(String username);

    void getNotificaton(User user);
}
