package com.MyProject.Feature_Tracking_Portal.Repository;

import com.MyProject.Feature_Tracking_Portal.Models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    // Inherits all CRUD methods like save(), findAll(), findById(), delete(), etc.
    // Add custom queries here if needed (e.g., findByStatus, findByStage).
}