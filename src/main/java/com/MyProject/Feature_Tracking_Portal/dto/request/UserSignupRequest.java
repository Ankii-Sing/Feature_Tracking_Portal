package com.MyProject.Feature_Tracking_Portal.dto.request;

import com.MyProject.Feature_Tracking_Portal.Enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequest {

        @NotBlank(message = "Email cannot be Empty!!!")
        @Email(message = "Email format not correct!!!")
        private String email;

        @NotBlank(message = "Name cannot be blank!!!")
        private String name;

        @NotBlank
        @Size(min = 8, message = "Password size should be at least 8!!!")
        private String password;

        @NotBlank(message = "Role cannot be blank")
        private UserRole role;
}
