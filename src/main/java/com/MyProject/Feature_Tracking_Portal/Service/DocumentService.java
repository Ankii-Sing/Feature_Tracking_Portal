package com.MyProject.Feature_Tracking_Portal.Service;

import com.MyProject.Feature_Tracking_Portal.Enums.DocumentType;
import com.MyProject.Feature_Tracking_Portal.Enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.Models.Document;
import com.MyProject.Feature_Tracking_Portal.Models.Feature;
import com.MyProject.Feature_Tracking_Portal.Models.User;
import com.MyProject.Feature_Tracking_Portal.Repository.DocumentRepository;
import com.MyProject.Feature_Tracking_Portal.Repository.FeatureRepository;
import com.MyProject.Feature_Tracking_Portal.Repository.UserRepository;
import com.MyProject.Feature_Tracking_Portal.dto.request.DocumentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private UserRepository userRepository;

    public String addDocument(DocumentRequest request, Long userId) {

        boolean isAssigned = featureRepository.isUserAssignedToFeature(request.getFeatureId(), userId);
        if (!isAssigned) {
            throw new RuntimeException("User is not assigned to this feature.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("Document Type is:" + request.getDocumentType());
        System.out.println("user Type is:" + user.getRole());

        // 3. Validate document type based on user role
        System.out.println( "The function returns "+canUploadDocument(user.getRole(), request.getDocumentType()));
        System.out.println("output of statement :" +( DocumentType.TECHNICAL_DOC == request.getDocumentType()));


        if (!canUploadDocument(user.getRole(), request.getDocumentType())) {
            throw new RuntimeException("User is not allowed to upload this document type.");
        }

        Feature feature = featureRepository.findByFeatureId(request.getFeatureId())
                .orElseThrow(() -> new RuntimeException("Feature not found with ID: " + request.getFeatureId()));

        // 4. Save document
        Document document = new Document();
        document.setFeatureId(feature);  // Assuming Feature has constructor with ID
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
                            documentType == DocumentType.DEV_TESTING_DOC);

            case QA_ENGINEER ->
                    (documentType == DocumentType.QA_DOC ||
                            documentType == DocumentType.PRE_POST_DOC ||
                            documentType == DocumentType.STAGING_DOC);

            default -> false;
        };
    }
}