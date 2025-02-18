package com.MyProject.Feature_Tracking_Portal.dto.request;

import com.MyProject.Feature_Tracking_Portal.enums.FeatureStage;
import com.MyProject.Feature_Tracking_Portal.enums.FeatureStatus;
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
    private Long assignedTo;
    private Long prodManager;
    private Long qaEngineer;
    private Long epicOwner;
}
