package com.MyProject.Feature_Tracking_Portal.Service;

import com.MyProject.Feature_Tracking_Portal.Enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.Models.User;
import com.MyProject.Feature_Tracking_Portal.Repository.UserRepository;
import com.MyProject.Feature_Tracking_Portal.dto.request.AuthenticationRequest;
import com.MyProject.Feature_Tracking_Portal.dto.request.RegisterRequest;
import com.MyProject.Feature_Tracking_Portal.dto.response.AuthenticationResponse;
import com.MyProject.Feature_Tracking_Portal.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

        // create user , authenticate and save it to the database and return generated token out of it.
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.REGULAR_USER)
                .build();

        userRepository.save(user);
        var jwtToken = "Bearer "+jwtService.genereateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new UsernameNotFoundException("User not found"));
        var jwtToken = "Bearer "+jwtService.genereateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}
