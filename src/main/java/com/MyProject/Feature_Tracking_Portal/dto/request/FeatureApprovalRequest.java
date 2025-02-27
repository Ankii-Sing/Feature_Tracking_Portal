package com.MyProject.Feature_Tracking_Portal.dto.request;

import com.MyProject.Feature_Tracking_Portal.enums.FeatureStage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureApprovalRequest {
    private Long featureId;
    private FeatureStage stage;
    private Boolean status;
    private Long userId;
}