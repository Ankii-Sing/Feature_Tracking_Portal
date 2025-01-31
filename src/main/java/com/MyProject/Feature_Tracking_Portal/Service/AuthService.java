package com.MyProject.Feature_Tracking_Portal.Service;

import com.MyProject.Feature_Tracking_Portal.Enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.Models.User;
import com.MyProject.Feature_Tracking_Portal.Repository.UserRepository;
import com.MyProject.Feature_Tracking_Portal.dto.request.UserSignupRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Use BCrypt for password hashing
    }

    @Transactional
    public String signUpUser(UserSignupRequest request) {
        // Check if user already exists
        if (userRepository.findByEmail(request.getEmail()) == null) {
            return "Email already in use!";
        }

        // Create new user with hashed password
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setName(request.getName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword())); // Hash password
//        newUser.setRole(UserRole.valueOf(request.getRole().toUpperCase())); // Convert string to Enum
        newUser.setRole(request.getRole());

        // Save user to DB
        userRepository.save(newUser);

        return "User registered successfully!";
    }

}

