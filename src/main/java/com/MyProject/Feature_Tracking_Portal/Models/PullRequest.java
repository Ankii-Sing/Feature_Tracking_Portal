package com.MyProject.Feature_Tracking_Portal.Models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Generates getters, setters, toString, equals, and hashCode
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