package com.MyProject.Feature_Tracking_Portal.service;

import com.MyProject.Feature_Tracking_Portal.dto.request.DocumentRequest;

public interface DocumentService {
    String addDocument(DocumentRequest request, Long userId);
}