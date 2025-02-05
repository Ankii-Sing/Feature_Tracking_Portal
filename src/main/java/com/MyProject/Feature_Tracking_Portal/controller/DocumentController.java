package com.MyProject.Feature_Tracking_Portal.controller;
import com.MyProject.Feature_Tracking_Portal.service.DocumentServiceImpl;
import com.MyProject.Feature_Tracking_Portal.dto.request.DocumentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/doc")
public class DocumentController {

    private final DocumentServiceImpl documentServiceImpl;

    public DocumentController(DocumentServiceImpl documentServiceImpl) {
        this.documentServiceImpl = documentServiceImpl;
    }

    @PostMapping
    public ResponseEntity<String> addDocument(@RequestBody DocumentRequest request) {
        Long userId = request.getUserId();
        try {
            documentServiceImpl.addDocument(request, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Document added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add document: " + e.getMessage());
        }
    }
}
