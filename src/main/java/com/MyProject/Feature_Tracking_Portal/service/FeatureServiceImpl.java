package com.MyProject.Feature_Tracking_Portal.service;
import com.MyProject.Feature_Tracking_Portal.enums.UserRole;
import com.MyProject.Feature_Tracking_Portal.exception.FeatureNotFoundException;
import com.MyProject.Feature_Tracking_Portal.models.Feature;
import com.MyProject.Feature_Tracking_Portal.models.User;
import com.MyProject.Feature_Tracking_Portal.repository.FeatureRepository;
import com.MyProject.Feature_Tracking_Portal.dto.request.FeatureRequest;
import com.MyProject.Feature_Tracking_Portal.dto.request.UpdateFeatureRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


@Service
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public FeatureServiceImpl(FeatureRepository featureRepository, UserServiceImpl userServiceImpl) {
        this.featureRepository = featureRepository;
        this.userServiceImpl = userServiceImpl;
    }

//    private static final List<UserRole> ALLOWED_USERS = Arrays.asList(ADMIN, EPIC_OWNER,PRODUCT_MANAGER);

    public void addFeatureDetails(FeatureRequest request, UserRole userRole) {


        if (!(userRole == UserRole.ADMIN || userRole == UserRole.EPIC_OWNER ||
                userRole == UserRole.PRODUCT_MANAGER)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied: You don't have permission to add feature details.");
        }

        Feature feature = new Feature();

        User assignedTo = userServiceImpl.findById(request.getAssignedTo());
        if(assignedTo.getRole() != UserRole.DEVELOPER) {
            throw new IllegalArgumentException("Assigned user must be a developer");
        }
        feature.setAssignedTo(assignedTo);

        User prodManager = userServiceImpl.findById(request.getProdManager());
        if(prodManager.getRole() != UserRole.PRODUCT_MANAGER) {
            throw new IllegalArgumentException("Product Manager must have the PRODUCT_MANAGER role");
        }
        feature.setProdManager(prodManager);

        User qaEngineer = userServiceImpl.findById(request.getQaEngineer());
        if(qaEngineer.getRole() != UserRole.QA_ENGINEER) {
            throw new IllegalArgumentException("QA Engineer must have the QA_ENGINEER role");
        }
        feature.setQaEngineer(qaEngineer);


        User epicOwner = userServiceImpl.findById(request.getEpicOwner());
        if(epicOwner.getRole() != UserRole.EPIC_OWNER) {
            throw new IllegalArgumentException("Epic Owner must have the EPIC_OWNER role");
        }
        feature.setEpicOwner(epicOwner);


        User created_by = userServiceImpl.findById(request.getCreated_by());

        feature.setTitle(request.getTitle());
        feature.setDescription(request.getDescription());
        feature.setDueDate(request.getDuedate());
        feature.setEpicOwner(epicOwner);
        feature.setCreatedBy(created_by);

        featureRepository.save(feature);
    }

    private void validateFeatureRequest(FeatureRequest request) {
        if (StringUtils.isEmpty(request.getTitle())) {
            throw new IllegalArgumentException("Feature title cannot be empty");
        }
    }

    public Feature getFeatureById(Long featureId) {
        return featureRepository.findByFeatureId(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found with ID: " + featureId));
    }


    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    // Feature is updated Successfully , i just need to add some more chekcs to make sure everything is what updating is Legit.
    // testing this api is left.
    public Feature updateFeature(Long featureId, Long userId, UpdateFeatureRequest request) {
        Optional<Feature> featureOptional = featureRepository.findById(featureId);
        if (featureOptional.isPresent()) {
            Feature feature = featureOptional.get();
            User updater = userServiceImpl.findById(userId);

            // Only ADMIN, EPIC_OWNER, or PRODUCT_MANAGER can update the feature
            if (!(updater.getRole() == UserRole.ADMIN || updater.getRole() == UserRole.EPIC_OWNER ||
                    updater.getRole() == UserRole.PRODUCT_MANAGER)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not authorized to update features");
            }

            System.out.println("Printing feature: " + feature);

            // Fetching users for assigned roles if provided in the request
            if (request.getAssignedTo() != null) {
                User assignedTo = userServiceImpl.findById(request.getAssignedTo());
                if (assignedTo.getRole() != UserRole.DEVELOPER) {
                    throw new IllegalArgumentException("Assigned user must be a developer");
                }
                feature.setAssignedTo(assignedTo);
            }


            if (request.getQaEngineer() != null) {
                User qaEngineer = userServiceImpl.findById(request.getQaEngineer());
                if (qaEngineer.getRole() != UserRole.QA_ENGINEER) {
                    throw new IllegalArgumentException("QA Engineer must have the QA_ENGINEER role");
                }
                feature.setQaEngineer(qaEngineer);
            }

            if (request.getProdManager() != null) {
                User prodManager = userServiceImpl.findById(request.getProdManager());
                if (prodManager.getRole() != UserRole.PRODUCT_MANAGER) {
                    throw new IllegalArgumentException("Product Manager must have the PRODUCT_MANAGER role");
                }
                feature.setProdManager(prodManager);
            }

            if (request.getEpicOwner() != null) {
                User epicOwner = userServiceImpl.findById(request.getEpicOwner());
                if (epicOwner.getRole() != UserRole.EPIC_OWNER) {
                    throw new IllegalArgumentException("Epic Owner must have the EPIC_OWNER role");
                }
                feature.setEpicOwner(epicOwner);
            }


            if (request.getTitle() != null) {
                feature.setTitle(request.getTitle());
            }
            if (request.getDescription() != null) {
                feature.setDescription(request.getDescription());
            }
            if (request.getDueDate() != null) {
                feature.setDueDate(request.getDueDate());
            }
            if (request.getStage() != null) {
                feature.setStage(request.getStage());
            }
            if (request.getStatus() != null) {
                feature.setStatus(request.getStatus());
            }

            return featureRepository.save(feature);
        } else {
            throw new FeatureNotFoundException("Feature not found with id: " + featureId);
        }
    }
}
