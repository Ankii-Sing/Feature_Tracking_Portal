package com.MyProject.Feature_Tracking_Portal.service;

import com.MyProject.Feature_Tracking_Portal.enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.models.User;
import java.util.List;

public interface UserService {
    public User findById(Long userId);
    public UserRole findRoleById(Long userId);
    public Long findUserIdByEmail(String email);
    public List<User> getAllUsers() ;
}
