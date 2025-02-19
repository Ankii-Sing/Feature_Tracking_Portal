package com.MyProject.Feature_Tracking_Portal.repository;
import com.MyProject.Feature_Tracking_Portal.models.Document;
import com.MyProject.Feature_Tracking_Portal.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByFeatureId(Feature feature);
}