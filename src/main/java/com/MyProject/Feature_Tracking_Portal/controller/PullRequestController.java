package com.MyProject.Feature_Tracking_Portal.controller;
import com.MyProject.Feature_Tracking_Portal.dto.request.PRUpdateStatusRequest;
import com.MyProject.Feature_Tracking_Portal.models.PullRequest;
import com.MyProject.Feature_Tracking_Portal.service.PullRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/public/pullrequest")
public class PullRequestController {

    @Autowired
    private PullRequestServiceImpl pullRequestServiceImpl;

    @PostMapping("/add/{featureId}")
    public ResponseEntity<String> addPullRequest(@PathVariable Long featureId, @RequestBody PullRequest request,@RequestHeader("Authorization") String token) {

        try {
            if(request.getLink().equals("")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The link we get is: " + request.getLink());
            }
            String response = pullRequestServiceImpl.addPullRequest(featureId, request.getLink());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add PullRequest");
        }
    }

    // fetch pull requests by featureId
    @GetMapping("/{featureId}")
    public ResponseEntity<List<PullRequest>> getPullRequestsByFeature(@PathVariable Long featureId,@RequestHeader("Authorization") String token) {
        List<PullRequest> pullRequests = pullRequestServiceImpl.getPullRequestsByFeature(featureId);

        if (pullRequests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pullRequests);
    }

    @PostMapping("/update-status")
    public ResponseEntity<String> updatePrStatus(@RequestBody PRUpdateStatusRequest request,@RequestHeader("Authorization") String token) {
        try {
            String responseMessage = pullRequestServiceImpl.updatePrStatus(request.getPullRequestId(), request.getPrStatus());
            return ResponseEntity.ok(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}