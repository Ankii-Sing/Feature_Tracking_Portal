package com.MyProject.Feature_Tracking_Portal.dto.request;
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
    private Long assignedTo;
    private LocalDate duedate;
    private Long prodManager;
    private Long qaEngineer;
    private Long epicOwner;
    private Long created_by;



}
