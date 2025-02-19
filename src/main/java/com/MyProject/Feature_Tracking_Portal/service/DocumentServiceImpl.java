package com.MyProject.Feature_Tracking_Portal.service;
import com.MyProject.Feature_Tracking_Portal.dto.request.DocumentRequest;
import com.MyProject.Feature_Tracking_Portal.enums.DocumentType;
import com.MyProject.Feature_Tracking_Portal.enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.exception.FeatureNotFoundException;
import com.MyProject.Feature_Tracking_Portal.exception.UnauthorizedDocumentUploadException;
import com.MyProject.Feature_Tracking_Portal.exception.UserNotFoundException;
import com.MyProject.Feature_Tracking_Portal.models.Document;
import com.MyProject.Feature_Tracking_Portal.models.Feature;
import com.MyProject.Feature_Tracking_Portal.models.User;
import com.MyProject.Feature_Tracking_Portal.repository.DocumentRepository;
import com.MyProject.Feature_Tracking_Portal.repository.FeatureRepository;
import com.MyProject.Feature_Tracking_Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final FeatureRepository featureRepository;
    private final UserRepository userRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, FeatureRepository featureRepository, UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.featureRepository = featureRepository;
        this.userRepository = userRepository;
    }

    public String addDocument(DocumentRequest request, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!canUploadDocument(user.getRole(), request.getDocumentType())) {
            throw new UnauthorizedDocumentUploadException("User is not allowed to upload this document type.");
        }

        Feature feature = featureRepository.findByFeatureId(request.getFeatureId())
                .orElseThrow(() -> new FeatureNotFoundException("Feature not found with ID: " + request.getFeatureId()));

        Document document = new Document();
        document.setFeatureId(feature);
        document.setUserId(user);
        document.setDocumentType(request.getDocumentType());
        document.setDocumentLink(request.getDocumentLink());

        documentRepository.save(document);
        return "Document added successfully.";
    }

    private boolean canUploadDocument(UserRole userRole, DocumentType documentType) {
        return switch (userRole) {
            case ADMIN, EPIC_OWNER -> true;

            case DEVELOPER ->
                    (documentType == DocumentType.TECHNICAL_DOC ||
                            documentType == DocumentType.DEV_TESTING_DOC ||
                            documentType == DocumentType.PRE_POST_DOC);

            case QA_ENGINEER ->
                    (documentType == DocumentType.QA_DOC ||
                            documentType == DocumentType.PRE_POST_DOC ||
                            documentType == DocumentType.STAGING_DOC);

            case PRODUCT_MANAGER ->
                    (documentType == DocumentType.STAGING_DOC);

            default -> false;
        };
    }

    public List<Document> getDocumentsByFeatureId(Long featureId) {
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new FeatureNotFoundException("Feature not found with ID: " + featureId));

        return documentRepository.findByFeatureId(feature);
    }
}