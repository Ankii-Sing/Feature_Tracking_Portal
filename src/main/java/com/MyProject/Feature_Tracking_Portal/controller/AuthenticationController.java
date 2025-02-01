package com.MyProject.Feature_Tracking_Portal.controller;
import com.MyProject.Feature_Tracking_Portal.Service.AuthenticationService;
import com.MyProject.Feature_Tracking_Portal.dto.request.AuthenticationRequest;
import com.MyProject.Feature_Tracking_Portal.dto.request.RegisterRequest;
import com.MyProject.Feature_Tracking_Portal.dto.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @ PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }



}