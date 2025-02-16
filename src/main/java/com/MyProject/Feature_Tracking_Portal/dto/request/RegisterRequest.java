package com.MyProject.Feature_Tracking_Portal.dto.request;
import com.MyProject.Feature_Tracking_Portal.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @JsonProperty()
    private String username;
    private String email;
    private String password;
    private UserRole userRole;

}

