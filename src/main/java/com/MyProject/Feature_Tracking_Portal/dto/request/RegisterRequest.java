package com.MyProject.Feature_Tracking_Portal.dto.request;
import com.MyProject.Feature_Tracking_Portal.enums.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private UserRole userRole;

}

