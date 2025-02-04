package com.MyProject.Feature_Tracking_Portal.Service;

import com.MyProject.Feature_Tracking_Portal.Enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.Models.User;
import com.MyProject.Feature_Tracking_Portal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    public UserRole findRoleById(Long userId) {
        return userRepository.findById(userId)
                .map(User::getRole)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }



}
