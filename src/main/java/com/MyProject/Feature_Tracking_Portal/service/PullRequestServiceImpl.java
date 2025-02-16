package com.MyProject.Feature_Tracking_Portal.service;
import com.MyProject.Feature_Tracking_Portal.models.Feature;
import com.MyProject.Feature_Tracking_Portal.models.PullRequest;
import com.MyProject.Feature_Tracking_Portal.repository.FeatureRepository;
import com.MyProject.Feature_Tracking_Portal.repository.PullRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PullRequestServiceImpl implements PullRequestService{

    private final PullRequestRepository pullRequestRepository;
    private final FeatureRepository featureRepository;

    @Autowired
    public PullRequestServiceImpl(PullRequestRepository pullRequestRepository, FeatureRepository featureRepository) {
        this.pullRequestRepository = pullRequestRepository;
        this.featureRepository = featureRepository;
    }

    @Transactional
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


    @Transactional
    public String updatePrStatus(Long pullRequestId, Boolean prStatus) {
        Optional<PullRequest> pullRequestOptional = pullRequestRepository.findById(pullRequestId);

        if (pullRequestOptional.isEmpty()) {
            throw new IllegalArgumentException("Pull Request with ID " + pullRequestId + " not found.");
        }

        PullRequest pullRequest = pullRequestOptional.get();
        pullRequest.setPrStatus(prStatus);
        pullRequestRepository.save(pullRequest);

        return "Pull Request status updated successfully!";
    }
}