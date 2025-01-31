package com.MyProject.Feature_Tracking_Portal.controller;

//import com.MyProject.Feature_Tracking_Portal.DeletedFiles.UserService;
import com.MyProject.Feature_Tracking_Portal.Service.AuthService;
import com.MyProject.Feature_Tracking_Portal.dto.request.UserLoginRequest;
import com.MyProject.Feature_Tracking_Portal.dto.request.UserSignupRequest;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserLoginController {
    @Autowired
    private final AuthService auth;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserSignupRequest request) {
        String response = auth.signUpUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequest request) {
//        String response = authService.loginUser(request);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
}




//POST
//POST
/// api/auth/signup
/// api/auth/login
//Description
//Register a new user
//User login and generate a token