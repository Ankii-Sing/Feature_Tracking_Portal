package com.MyProject.Feature_Tracking_Portal.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
        @NotBlank(message = "Email is required")
        @Email(message = "Email Format is Invalid")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Minimum length 8")
        private String password;
}
