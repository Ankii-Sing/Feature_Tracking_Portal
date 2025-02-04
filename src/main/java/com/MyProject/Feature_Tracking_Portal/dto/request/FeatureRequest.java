package com.MyProject.Feature_Tracking_Portal.dto.request;
import com.MyProject.Feature_Tracking_Portal.Models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FeatureRequest {
    private String title;
    private String description;
    // created by i need to handle while sending the request. (extract the user id from the token)
    private Long assignedTo;
    // date is also handled while sending the request.
    private LocalDate duedate;
    private Long prodManager;
    private Long qaEngineer;
    private Long epicOwner;
    private Long created_by;



}
