package com.MyProject.Feature_Tracking_Portal.controller;
import com.MyProject.Feature_Tracking_Portal.Models.Feature;
import com.MyProject.Feature_Tracking_Portal.Models.User;
import com.MyProject.Feature_Tracking_Portal.Service.DocumentService;
import com.MyProject.Feature_Tracking_Portal.dto.request.DocumentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/doc")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<String> addDocument(@RequestBody DocumentRequest request) {
        Long userId = request.getUserId();
        try {
            documentService.addDocument(request, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Document added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add document: " + e.getMessage());
        }
    }
}
