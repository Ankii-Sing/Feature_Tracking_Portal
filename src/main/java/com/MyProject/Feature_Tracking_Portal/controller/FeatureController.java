package com.MyProject.Feature_Tracking_Portal.controller;


import com.MyProject.Feature_Tracking_Portal.Enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.Exception.FeatureNotFoundException;
import com.MyProject.Feature_Tracking_Portal.Models.Feature;
import com.MyProject.Feature_Tracking_Portal.Service.FeatureService;
import com.MyProject.Feature_Tracking_Portal.Service.UserService;
import com.MyProject.Feature_Tracking_Portal.dto.request.FeatureRequest;
import com.MyProject.Feature_Tracking_Portal.dto.request.UpdateFeatureRequest;
import com.MyProject.Feature_Tracking_Portal.utils.JwtService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/public")
@Log
public class FeatureController {


    private final FeatureService featureService;
    private final UserService userService;

    public FeatureController(FeatureService featureService, UserService userService) {
        this.featureService = featureService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFeatureDetails( @RequestBody FeatureRequest request) {

        Long userID = (request.getCreated_by());
        UserRole userRole = userService.findRoleById((userID));

        featureService.addFeatureDetails(request, userRole);

        return ResponseEntity.ok("Feature details added successfully!");
    }

    @PostMapping("/update/{featureId}")
    public ResponseEntity<String> updateFeature(@RequestBody UpdateFeatureRequest request , @PathVariable Long featureId) {
        try {
            Feature updatedFeature = featureService.updateFeature(featureId, request);
            return ResponseEntity.ok("Feature updated successfully: " + updatedFeature.getFeatureId());
        } catch (FeatureNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feature not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update feature: " + e.getMessage());
        }
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