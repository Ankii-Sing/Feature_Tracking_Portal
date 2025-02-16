package com.MyProject.Feature_Tracking_Portal.dto.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PRUpdateStatusRequest {
    private Long pullRequestId;
    private Boolean prStatus; // true = Approved, false = Rejected
}