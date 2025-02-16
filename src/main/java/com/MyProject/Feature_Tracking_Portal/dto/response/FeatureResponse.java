package com.MyProject.Feature_Tracking_Portal.dto.response;

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
public class FeatureResponse {
    private Long id;
    String title;
    String description;
    private LocalDate due_date;
    private FeatureStage stage;
    private FeatureStatus status;
    private Long createdBy;
    private Long assignedTo;
    private boolean epicOwnerGoAheadStatus;
    private boolean prodGoAheadStatus;




}
