package com.MyProject.Feature_Tracking_Portal.service;

import com.MyProject.Feature_Tracking_Portal.enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.models.User;
import com.MyProject.Feature_Tracking_Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

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
