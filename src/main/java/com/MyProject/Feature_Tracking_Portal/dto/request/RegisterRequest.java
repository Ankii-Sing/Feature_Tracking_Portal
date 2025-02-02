package com.MyProject.Feature_Tracking_Portal.dto.request;
import com.MyProject.Feature_Tracking_Portal.Enums.UserRole;
import lombok.*;
import org.hibernate.usertype.UserType;

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

