package com.MyProject.Feature_Tracking_Portal.Service;
import com.MyProject.Feature_Tracking_Portal.Enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.Exception.FeatureNotFoundException;
import com.MyProject.Feature_Tracking_Portal.Models.Feature;
import com.MyProject.Feature_Tracking_Portal.Models.User;
import com.MyProject.Feature_Tracking_Portal.Repository.FeatureRepository;
import com.MyProject.Feature_Tracking_Portal.dto.request.FeatureRequest;
import com.MyProject.Feature_Tracking_Portal.dto.request.UpdateFeatureRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.MyProject.Feature_Tracking_Portal.Enums.UserRole.ADMIN;
import static com.MyProject.Feature_Tracking_Portal.Enums.UserRole.EPIC_OWNER;
import static com.MyProject.Feature_Tracking_Portal.Enums.UserRole.PRODUCT_MANAGER;

@Service
public class FeatureService {

    private final FeatureRepository featureRepository;
    private final UserService userService;

    @Autowired
    public FeatureService(FeatureRepository featureRepository, UserService userService) {
        this.featureRepository = featureRepository;
        this.userService = userService;
    }

    private static final List<UserRole> ALLOWED_USERS = Arrays.asList(ADMIN, EPIC_OWNER,PRODUCT_MANAGER);

    public void addFeatureDetails(FeatureRequest request, UserRole userRole) {
        if (!ALLOWED_USERS.contains(userRole)) {
            throw new RuntimeException("Access Denied: You don't have permission to add feature details.");
        }

        User assignedTo = userService.findById(request.getAssignedTo());
        User prodManager = userService.findById(request.getProdManager());
        User qaEngineer = userService.findById(request.getQaEngineer());
        User epicOwner = userService.findById(request.getEpicOwner());
        User created_by = userService.findById(request.getCreated_by());


        Feature feature = new Feature();
        feature.setTitle(request.getTitle());
        feature.setDescription(request.getDescription());
        feature.setDueDate(request.getDuedate());
        feature.setAssignedTo(assignedTo);
        feature.setProdManager(prodManager);
        feature.setQaEngineer(qaEngineer);
        feature.setEpicOwner(epicOwner);
        feature.setCreatedBy(created_by);

        featureRepository.save(feature);
    }

    public Feature getFeatureById(Long featureId) {
        return featureRepository.findByFeatureId(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found with ID: " + featureId));
    }


    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    public Feature updateFeature(Long featureId, UpdateFeatureRequest request) {
        Optional<Feature> featureOptional = featureRepository.findById(featureId);
        if (featureOptional.isPresent()) {
            Feature feature = featureOptional.get();


            User assignedTo = userService.findById(request.getAssignedTo());
            User prodManager = userService.findById(request.getProdManager());
            User qaEngineer = userService.findById(request.getQaEngineer());
            User epicOwner = userService.findById(request.getEpicOwner());


            // Update feature fields only if it is changed or not null.

            if(request.getDescription() != null && !request.getDescription().isEmpty() && request.getDescription().equals(getFeatureById(featureId).getDescription())) {
                feature.setDescription(request.getDescription());
            }
            if(request.getTitle() != null && !request.getTitle().isEmpty() && request.getTitle().equals(getFeatureById(featureId).getTitle())) {
                feature.setTitle(request.getTitle());
            }

            if(request.getDueDate() != null && request.getDueDate().equals(getFeatureById(featureId).getDueDate())) {
                feature.setDueDate(request.getDueDate());
            }

            if(request.getStage() != null && request.getStage().equals(getFeatureById(featureId).getStage())) {
                feature.setStage(request.getStage());
            }

            if(request.getStatus() != null && request.getStatus().equals(getFeatureById(featureId).getStatus())) {
                feature.setStatus(request.getStatus());
            }

            if(request.getAssignedTo() != null && request.getAssignedTo().equals(getFeatureById(featureId).getAssignedTo())) {
                feature.setStatus(request.getStatus());
            }

            if(request.getAssignedTo() != null && request.getAssignedTo().equals(getFeatureById(featureId).getAssignedTo())) {
                feature.setStatus(request.getStatus());
            }

            if(request.getProdManager() != null && request.getProdManager().equals(getFeatureById(featureId).getProdManager())) {
                feature.setStatus(request.getStatus());
            }

            if(request.getQaEngineer() != null && request.getQaEngineer().equals(getFeatureById(featureId).getQaEngineer())) {
                feature.setStatus(request.getStatus());
            }

            if(request.getEpicOwner() != null && request.getEpicOwner().equals(getFeatureById(featureId).getEpicOwner())) {
                feature.setStatus(request.getStatus());
            }


            return featureRepository.save(feature);
        } else {
            throw new FeatureNotFoundException("Feature not found with id: " + featureId);
        }
    }
}
