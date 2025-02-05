package com.MyProject.Feature_Tracking_Portal.models;
import com.MyProject.Feature_Tracking_Portal.enums.DocumentType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "feature_id", referencedColumnName = "feature_id", nullable = false)
    private Feature featureId;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "document_link", nullable = false, length = 512)
    private String documentLink;

}
