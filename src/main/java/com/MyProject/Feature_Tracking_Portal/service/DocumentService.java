package com.MyProject.Feature_Tracking_Portal.service;

import com.MyProject.Feature_Tracking_Portal.dto.request.DocumentRequest;
import com.MyProject.Feature_Tracking_Portal.models.Document;

import java.util.List;

public interface DocumentService {
    String addDocument(DocumentRequest request, Long userId);
    List<Document> getDocumentsByFeatureId(Long featureId);
}