package com.MyProject.Feature_Tracking_Portal.repository;

import com.MyProject.Feature_Tracking_Portal.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;


@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Optional<Feature> findByFeatureId(Long featureId);
    List<Feature> findAll();

}