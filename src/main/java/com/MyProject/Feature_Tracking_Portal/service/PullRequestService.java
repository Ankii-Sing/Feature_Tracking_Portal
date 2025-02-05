package com.MyProject.Feature_Tracking_Portal.service;

import com.MyProject.Feature_Tracking_Portal.models.PullRequest;
import java.util.List;

public interface PullRequestService {
    String addPullRequest(Long featureId, String githubLink);
    List<PullRequest> getPullRequestsByFeature(Long featureId);
}
