package com.MyProject.Feature_Tracking_Portal.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pullrequest")
public class PullRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pullrequest_id")
    private Long pullrequest_id;

    @ManyToOne
    @JoinColumn(name = "feature_id", referencedColumnName = "feature_id", nullable = false)
    private Feature feature;

    @Column(name = "github_link", nullable = false, length = 512)
    private String Link;

    public String Link() {
        return Link;
    }

}