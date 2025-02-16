package com.MyProject.Feature_Tracking_Portal.controller;
import com.MyProject.Feature_Tracking_Portal.models.Document;
import com.MyProject.Feature_Tracking_Portal.service.DocumentServiceImpl;
import com.MyProject.Feature_Tracking_Portal.dto.request.DocumentRequest;
import com.MyProject.Feature_Tracking_Portal.service.UserService;
import com.MyProject.Feature_Tracking_Portal.utils.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/public/doc")
public class DocumentController {

    private final DocumentServiceImpl documentServiceImpl;
    private final UserService userService;
    private final JwtService jwtService;

    public DocumentController(DocumentServiceImpl documentServiceImpl, UserService userService, JwtService jwtService) {
        this.documentServiceImpl = documentServiceImpl;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<String> addDocument(@RequestBody DocumentRequest request
            ,@RequestHeader("Authorization") String token
    ) {

        System.out.println("document link is: " + request.getDocumentLink());
//        check if link is null
        if(request.getDocumentLink().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Document link is required");
        }
        System.out.println("recieved token in add document: " + token);
        System.out.println(" request comming from frontend: : " + request);
        token = token.substring(7);
        System.out.println("token after removing beaker: " + token);
        String email = jwtService.extractEmail(token);

        System.out.println("extractEmail: " + email);

//        Long userId = userService.findUserIdByEmail(email);

//        System.out.println("extractId: " + userId);

//        System.out.println("id extracted form the token: " + userId);

        Long userId = request.getUserId();


        System.out.println("user id extracted form the request body : " + userId);
        try {
            documentServiceImpl.addDocument(request, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Document added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add document: " + e.getMessage());
        }
    }

//     GET all documents for a given feature ID
    @GetMapping("/{featureId}")
    public ResponseEntity<List<Document>> getDocumentsByFeature(@PathVariable Long featureId
    ,@RequestHeader("Authorization") String token) {

        System.out.println("token in get all document :" + token );
        token = token.substring(7);
        System.out.println("token after removing beaker: " + token);

        List<Document> documents = documentServiceImpl.getDocumentsByFeatureId(featureId);
        return ResponseEntity.ok(documents);
    }




}
