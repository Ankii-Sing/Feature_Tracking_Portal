package com.MyProject.Feature_Tracking_Portal.controller;
import com.MyProject.Feature_Tracking_Portal.dto.request.FeatureApprovalRequest;
import com.MyProject.Feature_Tracking_Portal.enums.FeatureStage;
import com.MyProject.Feature_Tracking_Portal.enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.exception.FeatureNotFoundException;
import com.MyProject.Feature_Tracking_Portal.models.Feature;
import com.MyProject.Feature_Tracking_Portal.service.FeatureServiceImpl;
import com.MyProject.Feature_Tracking_Portal.service.UserService;
import com.MyProject.Feature_Tracking_Portal.service.UserServiceImpl;
import com.MyProject.Feature_Tracking_Portal.dto.request.FeatureRequest;
import com.MyProject.Feature_Tracking_Portal.dto.request.UpdateFeatureRequest;
import com.MyProject.Feature_Tracking_Portal.utils.JwtService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/public/feature")
@Log
public class FeatureController {


    private final FeatureServiceImpl featureServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final JwtService jwtService;
    private final UserService userService;

    public FeatureController(FeatureServiceImpl featureServiceImpl, UserServiceImpl userServiceImpl, JwtService jwtService, UserService userService) {
        this.featureServiceImpl = featureServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFeatureDetails( @RequestBody FeatureRequest request
            ,@RequestHeader("Authorization") String token) {

        token = token.substring(7);
        Long userID = (request.getCreated_by());
        UserRole userRole = userServiceImpl.findRoleById((userID));
        featureServiceImpl.addFeatureDetails(request, userRole);
        return ResponseEntity.ok("Feature details added successfully!");
    }

    @PostMapping("/update/{featureId}/{userId}")
    public ResponseEntity<String> updateFeature(@PathVariable Long featureId,@PathVariable Long userId
            ,@RequestBody UpdateFeatureRequest request
            ,@RequestHeader("Authorization") String token
    ) {

        log.info("I'M HERE IN THE UPDATE FEATURE CONTROLLER");

        System.out.println("Received Token in add features deatis: " + token);
                System.out.println("i am in updateFeature method");
        try {
            System.out.println("inside updateFeature method try method");
            Feature updatedFeature = featureServiceImpl.updateFeature(featureId,userId, request);
            return ResponseEntity.ok("Feature updated successfully: " + updatedFeature.getFeatureId());
        } catch (FeatureNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feature not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update feature: " + e.getMessage());
        }
    }

    @GetMapping("/{featureId}")
    public ResponseEntity<Feature> getFeatureByFeatureId(@PathVariable Long featureId
            ,@RequestHeader("Authorization") String token) {
        Feature feature = featureServiceImpl.getFeatureById(featureId);
        return ResponseEntity.ok(feature);
    }

    @GetMapping("/")
    public ResponseEntity<List<Feature>> getAllFeatures( ){
        List<Feature> features = featureServiceImpl.getAllFeatures();
        return ResponseEntity.ok(features);
    }

// endpoint for adding approval requst.
    @PostMapping("/approval")
    public ResponseEntity<String> updateFeatureApproval(@RequestBody FeatureApprovalRequest request ,@RequestHeader("Authorization") String token) {

        UserRole userRole = userServiceImpl.findRoleById(request.getUserId());
        Long FeatureID = request.getFeatureId();

        FeatureStage stage = request.getStage();
        if (stage.equals(FeatureStage.PRODUCT_GO_AHEAD) &&
                !(userRole.equals(UserRole.ADMIN) || userRole.equals(UserRole.PRODUCT_MANAGER))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to approve Product Go-Ahead.");
        }

        if (stage.equals(FeatureStage.EPIC_OWNER_GO_AHEAD) &&
                !(userRole.equals(UserRole.ADMIN)|| userRole.equals(UserRole.EPIC_OWNER))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to approve Epic Owner Go-Ahead.");
        }

        boolean isUpdated = featureServiceImpl.updateFeatureApproval(FeatureID, request.getStatus(), stage);

        if (isUpdated) {
            return ResponseEntity.ok("Feature approval status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating approval status.");
        }
    }
}



