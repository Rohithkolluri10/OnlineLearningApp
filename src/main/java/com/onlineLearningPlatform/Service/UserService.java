package com.onlineLearningPlatform.Service;

import com.onlineLearningPlatform.dto.RegisterDto;
import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.model.UserPrincipal;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();

    User getUserById(Long id);

    void blockuser(User user1);

    void updateUserRoles(User user, String admin);

    User getUserbyUsername(String username);

    Optional<User> getUserbyEmail(String emailadress);

    User registeruser(@Valid RegisterDto registerDto);

    UserPrincipal getUserbyName(String username);
}
