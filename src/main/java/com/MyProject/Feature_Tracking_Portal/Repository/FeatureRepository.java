package com.MyProject.Feature_Tracking_Portal.Repository;

import com.MyProject.Feature_Tracking_Portal.Models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;


@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

    @Query("SELECT COUNT(f) > 0 FROM Feature f WHERE f.featureId = :featureId AND f.assignedTo.userId = :userId")
    boolean isUserAssignedToFeature(@Param("featureId") Long featureId, @Param("userId") Long userId);

    Optional<Feature> findByFeatureId(Long featureId);
    List<Feature> findAll();


}