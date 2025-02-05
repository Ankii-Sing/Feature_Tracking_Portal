package com.MyProject.Feature_Tracking_Portal.controller;


import com.MyProject.Feature_Tracking_Portal.enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.exception.FeatureNotFoundException;
import com.MyProject.Feature_Tracking_Portal.models.Feature;
import com.MyProject.Feature_Tracking_Portal.service.FeatureServiceImpl;
import com.MyProject.Feature_Tracking_Portal.service.UserServiceImpl;
import com.MyProject.Feature_Tracking_Portal.dto.request.FeatureRequest;
import com.MyProject.Feature_Tracking_Portal.dto.request.UpdateFeatureRequest;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/public/feature")
@Log
public class FeatureController {


    private final FeatureServiceImpl featureServiceImpl;
    private final UserServiceImpl userServiceImpl;

    public FeatureController(FeatureServiceImpl featureServiceImpl, UserServiceImpl userServiceImpl) {
        this.featureServiceImpl = featureServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFeatureDetails( @RequestBody FeatureRequest request) {

        Long userID = (request.getCreated_by());
        UserRole userRole = userServiceImpl.findRoleById((userID));
        featureServiceImpl.addFeatureDetails(request, userRole);
        return ResponseEntity.ok("Feature details added successfully!");
    }

    @PostMapping("/update/{featureId}/{userId}")
    public ResponseEntity<String> updateFeature(@PathVariable Long featureId,@PathVariable Long userId,@RequestBody UpdateFeatureRequest request) {
        try {
            Feature updatedFeature = featureServiceImpl.updateFeature(featureId,userId, request);
            return ResponseEntity.ok("Feature updated successfully: " + updatedFeature.getFeatureId());
        } catch (FeatureNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feature not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update feature: " + e.getMessage());
        }
    }

    @GetMapping("/{featureId}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable Long featureId) {
        Feature feature = featureServiceImpl.getFeatureById(featureId);
        return ResponseEntity.ok(feature);  // Return the feature data with status 200
    }

    @GetMapping("/")
    public ResponseEntity<List<Feature>> getAllFeatures() {
        List<Feature> features = featureServiceImpl.getAllFeatures();  // Call service method to get features
        return ResponseEntity.ok(features);  // Return the list of features with a 200 OK response
    }
}

// yha pe jWtService layer ki dikkat hai.vo hatane padegi.