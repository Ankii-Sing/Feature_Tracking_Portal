package com.MyProject.Feature_Tracking_Portal.service;
import com.MyProject.Feature_Tracking_Portal.models.Feature;
import com.MyProject.Feature_Tracking_Portal.models.PullRequest;
import com.MyProject.Feature_Tracking_Portal.repository.FeatureRepository;
import com.MyProject.Feature_Tracking_Portal.repository.PullRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PullRequestServiceImpl implements PullRequestService{

    private final PullRequestRepository pullRequestRepository;
    private final FeatureRepository featureRepository;

    @Autowired
    public PullRequestServiceImpl(PullRequestRepository pullRequestRepository, FeatureRepository featureRepository) {
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