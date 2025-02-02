package com.MyProject.Feature_Tracking_Portal.Service;
import com.MyProject.Feature_Tracking_Portal.Models.Feature;
import com.MyProject.Feature_Tracking_Portal.Repository.FeatureRepository;
import com.MyProject.Feature_Tracking_Portal.dto.request.FeatureRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FeatureService {

    private final FeatureRepository featureRepository;

    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    // Allowed user IDs for restricted access
private static final List<String> ALLOWED_USERS = Arrays.asList("ADMIN", "EPIC_OWNER", "PROD_MANAGER");

    public void addFeatureDetails(FeatureRequest request, String userId) {
        // Authorization check
        if (!ALLOWED_USERS.contains(userId)) {
            throw new RuntimeException("Access Denied: You don't have permission to add feature details.");
        }

        // Convert request DTO to Entity
        Feature feature = new Feature();
        feature.setTitle(request.getTitle());
        feature.setDescription(request.getDescription());
        feature.setDueDate(request.getDuedate());
        feature.setAssignedTo(request.getAssignedTo());
        feature.setProdManager(request.getProdManager());
        feature.setQaEngineer(request.getQaEngineer());

        // Save feature details
        featureRepository.save(feature);
    }

    public Feature getFeatureById(Long featureId) {
        // Fetch the feature using the repository method
        return featureRepository.findByFeatureId(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found with ID: " + featureId));
    }

    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();  // Fetch all features from the database
    }
}
