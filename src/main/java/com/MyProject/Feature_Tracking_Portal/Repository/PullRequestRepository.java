package com.MyProject.Feature_Tracking_Portal.Repository;
import com.MyProject.Feature_Tracking_Portal.Models.PullRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PullRequestRepository extends JpaRepository<PullRequest, Long> {

    List<PullRequest> findByFeature_FeatureId(Long featureId);
}