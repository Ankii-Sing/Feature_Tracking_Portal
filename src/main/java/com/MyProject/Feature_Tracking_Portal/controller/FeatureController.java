package com.MyProject.Feature_Tracking_Portal.controller;


import com.MyProject.Feature_Tracking_Portal.Models.Feature;
import com.MyProject.Feature_Tracking_Portal.Service.FeatureService;
import com.MyProject.Feature_Tracking_Portal.dto.request.FeatureRequest;
import com.MyProject.Feature_Tracking_Portal.utils.JwtService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/public")
@Log
public class FeatureController {

    private final FeatureService featureService;
    private final JwtService jwtUtil;

    public FeatureController(FeatureService featureService, JwtService jwtUtil) {
        this.featureService = featureService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFeatureDetails(
            @RequestBody FeatureRequest request,
            @RequestHeader("Authorization") String token) {

        log.info(String.valueOf(token.length()));
        // Extract user ID from JWT Token
        String userId = String.valueOf(jwtUtil.extractUserId(token.substring(7).toString())); // Remove "Bearer "

        // Call service to add feature details
        featureService.addFeatureDetails(request, userId);

        return ResponseEntity.ok("Feature details added successfully!");
    }

    @GetMapping("/{featureId}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable Long featureId) {
        Feature feature = featureService.getFeatureById(featureId);
        return ResponseEntity.ok(feature);  // Return the feature data with status 200
    }

    @GetMapping("/")
    public ResponseEntity<List<Feature>> getAllFeatures() {
        List<Feature> features = featureService.getAllFeatures();  // Call service method to get features
        return ResponseEntity.ok(features);  // Return the list of features with a 200 OK response
    }
}

// yha pe jWtService layer ki dikkat hai.vo hatane padegi.