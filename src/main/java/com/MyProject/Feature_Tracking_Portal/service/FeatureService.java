package com.MyProject.Feature_Tracking_Portal.service;


import com.MyProject.Feature_Tracking_Portal.dto.request.FeatureRequest;
import com.MyProject.Feature_Tracking_Portal.dto.request.UpdateFeatureRequest;
import com.MyProject.Feature_Tracking_Portal.enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.models.Feature;
import java.util.List;

public interface FeatureService {
    void addFeatureDetails(FeatureRequest request, UserRole userRole);
    Feature getFeatureById(Long featureId);
    List<Feature> getAllFeatures();
    Feature updateFeature(Long featureId, Long userId, UpdateFeatureRequest request);
}