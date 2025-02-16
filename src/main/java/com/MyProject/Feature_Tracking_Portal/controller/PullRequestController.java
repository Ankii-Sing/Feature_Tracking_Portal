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
    public ResponseEntity<String> addPullRequest(@PathVariable Long featureId, @RequestBody PullRequest request
            ,@RequestHeader("Authorization") String token) {

        System.out.println("token in add Pull Request: " + token );
        System.out.println("request body  " + request );

        try {
            System.out.println("the link we get is: " + request.getLink());
//            String link = request here it is null nedd to check.
            if(request.getLink().equals("")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The link we get is: " + request.getLink());
            }
            String response = pullRequestServiceImpl.addPullRequest(featureId, request.getLink());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add PullRequest");
        }
    }

    // New GET endpoint to fetch pull requests by featureId
    @GetMapping("/{featureId}")
    public ResponseEntity<List<PullRequest>> getPullRequestsByFeature(@PathVariable Long featureId
            ,@RequestHeader("Authorization") String token) {

        System.out.println("token in GET Pull Request: " + token );
        List<PullRequest> pullRequests = pullRequestServiceImpl.getPullRequestsByFeature(featureId);

        if (pullRequests.isEmpty()) {
            return ResponseEntity.noContent().build();  // Returns 204 No Content if no pull requests are found
        }

        return ResponseEntity.ok(pullRequests);  // Returns 200 OK with the list of pull requests
    }

    @PostMapping("/update-status")
    public ResponseEntity<String> updatePrStatus(@RequestBody PRUpdateStatusRequest request
            ,@RequestHeader("Authorization") String token) {

        System.out.println("token in add PRStatusRequest : " + token );
        try {
            String responseMessage = pullRequestServiceImpl.updatePrStatus(request.getPullRequestId(), request.getPrStatus());
            return ResponseEntity.ok(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}