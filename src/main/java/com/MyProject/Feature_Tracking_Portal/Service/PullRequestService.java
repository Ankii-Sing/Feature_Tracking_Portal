package com.MyProject.Feature_Tracking_Portal.Service;
import com.MyProject.Feature_Tracking_Portal.Models.Feature;
import com.MyProject.Feature_Tracking_Portal.Models.PullRequest;
import com.MyProject.Feature_Tracking_Portal.Repository.FeatureRepository;
import com.MyProject.Feature_Tracking_Portal.Repository.PullRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PullRequestService {

    private final PullRequestRepository pullRequestRepository;
    private final FeatureRepository featureRepository;

    @Autowired
    public PullRequestService(PullRequestRepository pullRequestRepository, FeatureRepository featureRepository) {
        this.pullRequestRepository = pullRequestRepository;
        this.featureRepository = featureRepository;
    }

    public String addPullRequest(Long featureId, String githubLink) {

        Feature feature = featureRepository.findById(featureId).orElseThrow(() -> new RuntimeException("Feature not found"));

        PullRequest pullRequest = new PullRequest();
        pullRequest.setFeature(feature);
        pullRequest.setLink(githubLink);

        pullRequestRepository.save(pullRequest);
        return "PullRequest added successfully";
    }

    public List<PullRequest> getPullRequestsByFeature(Long featureId) {
        return pullRequestRepository.findByFeature_FeatureId(featureId);
    }
}