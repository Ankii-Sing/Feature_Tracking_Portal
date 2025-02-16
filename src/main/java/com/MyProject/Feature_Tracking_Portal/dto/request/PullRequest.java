package com.MyProject.Feature_Tracking_Portal.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PullRequest {
    private String Link;
    private Boolean prStatus = null;
}
