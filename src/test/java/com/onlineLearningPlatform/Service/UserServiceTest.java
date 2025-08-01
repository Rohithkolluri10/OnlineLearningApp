package com.onlineLearningPlatform.Service;

import com.onlineLearningPlatform.Service.Impl.UserServiceImpl;
import com.onlineLearningPlatform.dto.RegisterDto;
import com.onlineLearningPlatform.dto.UserDto;
import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.model.UserRole;
import com.onlineLearningPlatform.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

import static com.onlineLearningPlatform.model.UserRole.ADMIN;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User sampleUser;

    private RegisterDto registerDto;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setName("John Doe");
        sampleUser.setEmailAddress("john@example.com");
        sampleUser.setPassword("password123");
        sampleUser.setBlocked(false);
        sampleUser.setActive(true);
        sampleUser.setRoles(List.of(UserRole.STUDENT));

        registerDto = new RegisterDto();
    }

    @Test
    void createUser_shouldReturnSavedUser() {
        Mockito.when(passwordEncoder.encode(Mockito.anyString()))
                .thenReturn("encoded-password");

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(sampleUser);

        registerDto.setUsername(sampleUser.getName());
        registerDto.setEmailadress(sampleUser.getEmailAddress());
        registerDto.setPassword(sampleUser.getPassword());
        User saved = userService.registeruser(registerDto);

        Assertions.assertNotNull(saved);
        Assertions.assertEquals("John Doe", saved.getName());
        Assertions.assertEquals("john@example.com", saved.getEmailAddress());
        Assertions.assertTrue(saved.getRoles().contains(UserRole.STUDENT));
    }
}