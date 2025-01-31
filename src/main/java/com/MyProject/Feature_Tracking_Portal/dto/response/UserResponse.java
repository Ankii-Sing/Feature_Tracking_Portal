package com.MyProject.Feature_Tracking_Portal.dto.response;

public record UserResponse(
        Long id,
        String name,
        String email,
        String password,
        String role)
{}

