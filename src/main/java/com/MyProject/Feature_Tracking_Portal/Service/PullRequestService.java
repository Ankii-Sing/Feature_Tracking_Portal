package com.MyProject.Feature_Tracking_Portal.Service;
import com.MyProject.Feature_Tracking_Portal.Models.Feature;
import com.MyProject.Feature_Tracking_Portal.Models.PullRequest;
import com.MyProject.Feature_Tracking_Portal.Models.User;
import com.MyProject.Feature_Tracking_Portal.Repository.FeatureRepository;
import com.MyProject.Feature_Tracking_Portal.Repository.PullRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PullRequestService {

    private PullRequestRepository pullRequestRepository;
    private FeatureRepository featureRepository;

    public String addPullRequest(Long featureId, String githubLink) {
        // Check if the feature exists
        Feature feature = featureRepository.findById(featureId).orElseThrow(() -> new RuntimeException("Feature not found"));

//        User developer = userService.findById(developerId);
//       if (!developer.getRole().equals("DEVELOPER")) {
//
//       }
//        User qa = userService.findById(QAId);
//        User epic = userService.findById(epicId);
//        User product = userService.findById(productId);
//
//        Feature featureToAdd = new Feature();
//        featureRepository


        // Create a new PullRequest object
        PullRequest pullRequest = new PullRequest();
        pullRequest.setFeature(feature);
        pullRequest.setLink(githubLink);

        // Save the PullRequest in the database
        pullRequestRepository.save(pullRequest);

        return "PullRequest added successfully";
    }

    public List<PullRequest> getPullRequestsByFeature(Long featureId) {
        return pullRequestRepository.findByFeature_FeatureId(featureId);
    }
}