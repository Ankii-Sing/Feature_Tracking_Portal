package com.MyProject.Feature_Tracking_Portal.dto.request;

import com.MyProject.Feature_Tracking_Portal.Enums.FeatureStage;
import com.MyProject.Feature_Tracking_Portal.Enums.FeatureStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFeatureRequest {
    private String description;
    private String title;
    private LocalDate dueDate;
    private FeatureStage stage;
    private FeatureStatus status;

// DEVELOPER RELATED UPDATES
    private Long assignedTo;
    private Long prodManager;
    private Long qaEngineer;
    private Long epicOwner;
}
