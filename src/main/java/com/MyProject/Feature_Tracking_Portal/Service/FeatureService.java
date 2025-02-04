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

import java.sql.SQLOutput;
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

    // Feature is updated Successfully , i just need to add some more chekcs to make sure everything is what updating is Legit.

    public Feature updateFeature(Long featureId, UpdateFeatureRequest request) {
        Optional<Feature> featureOptional = featureRepository.findById(featureId);
        if (featureOptional.isPresent()) {
            Feature feature = featureOptional.get();

            System.out.println("Printing feature: " + feature);

            // Fetching users for assigned roles if provided in the request
            User assignedTo = (request.getAssignedTo() != null) ? userService.findById(request.getAssignedTo()) : feature.getAssignedTo();
            User prodManager = (request.getProdManager() != null) ? userService.findById(request.getProdManager()) : feature.getProdManager();
            User qaEngineer = (request.getQaEngineer() != null) ? userService.findById(request.getQaEngineer()) : feature.getQaEngineer();
            User epicOwner = (request.getEpicOwner() != null) ? userService.findById(request.getEpicOwner()) : feature.getEpicOwner();

            System.out.println("DEscription of new feature is :" + request.getDescription());
            System.out.println("Titile of new feature is :" + request.getTitle());
            System.out.println("DueDate of new feature is :" + request.getDueDate());
            System.out.println("New feature is assignedTo :" + request.getAssignedTo());

            System.out.println("Control reaches here: " + assignedTo + " " + prodManager + " " + qaEngineer + " " + epicOwner);

            // ✅ Updating only when the new value is different
            if (request.getDescription() != null && !request.getDescription().equals(feature.getDescription())) {
                feature.setDescription(request.getDescription());
            }
            if (request.getTitle() != null && !request.getTitle().equals(feature.getTitle())) {
                feature.setTitle(request.getTitle());
            }
            if (request.getDueDate() != null && !request.getDueDate().equals(feature.getDueDate())) {
                feature.setDueDate(request.getDueDate());
            }
            if (request.getStage() != null && !request.getStage().equals(feature.getStage())) {
                feature.setStage(request.getStage());
            }
            if (request.getStatus() != null && !request.getStatus().equals(feature.getStatus())) {
                feature.setStatus(request.getStatus());
            }
            if (request.getAssignedTo() != null && !request.getAssignedTo().equals(feature.getAssignedTo())) {
                feature.setAssignedTo(assignedTo);
            }
            if (request.getProdManager() != null && !request.getProdManager().equals(feature.getProdManager())) {
                feature.setProdManager(prodManager);
            }
            if (request.getQaEngineer() != null && !request.getQaEngineer().equals(feature.getQaEngineer())) {
                feature.setQaEngineer(qaEngineer);
            }
            if (request.getEpicOwner() != null && !request.getEpicOwner().equals(feature.getEpicOwner())) {
                feature.setEpicOwner(epicOwner);
            }

            return featureRepository.save(feature);
        } else {
            throw new FeatureNotFoundException("Feature not found with id: " + featureId);
        }
    }
}
