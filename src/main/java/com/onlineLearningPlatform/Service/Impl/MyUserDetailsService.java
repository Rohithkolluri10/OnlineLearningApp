package com.onlineLearningPlatform.Service.Impl;

import com.onlineLearningPlatform.model.SecUser;
import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        System.out.println("DEBUG: MyUserDetailsService trying to load user by email: " + emailAddress);
        Optional<User> user =  userRepository.findByEmailAddress(emailAddress);
        if (user.isEmpty()){
            throw  new UsernameNotFoundException("User is not found");
        }
        return new SecUser(user.orElse(null));

    }
}
