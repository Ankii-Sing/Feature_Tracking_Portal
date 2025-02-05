package com.MyProject.Feature_Tracking_Portal.service;

import com.MyProject.Feature_Tracking_Portal.dto.request.AuthenticationRequest;
import com.MyProject.Feature_Tracking_Portal.dto.request.RegisterRequest;
import com.MyProject.Feature_Tracking_Portal.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
