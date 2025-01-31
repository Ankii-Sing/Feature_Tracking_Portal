package com.MyProject.Feature_Tracking_Portal.Models;

import com.MyProject.Feature_Tracking_Portal.Enums.FeatureStage;
import com.MyProject.Feature_Tracking_Portal.Enums.FeatureStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data  // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // Generates a no-args constructor
@Entity
@Table(name = "feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id")
    private Long featureId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", nullable = false, length = 512)
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to", referencedColumnName = "user_id")
    private User assignedTo;

    @Enumerated(EnumType.STRING)
    private FeatureStatus status = FeatureStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private FeatureStage stage;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "prod_go_ahead_status")
    private Boolean prodGoAheadStatus = false;

    @Column(name = "epic_owner_go_ahead_status")
    private Boolean epicOwnerGoAheadStatus = false;

    @ManyToOne
    @JoinColumn(name = "prod_manager", referencedColumnName = "user_id")
    private User prodManager;

    @ManyToOne
    @JoinColumn(name = "qa_engineer", referencedColumnName = "user_id")
    private User qaEngineer;

    @ManyToOne
    @JoinColumn(name = "epic_owner", referencedColumnName = "user_id")
    private User epicOwner;

}