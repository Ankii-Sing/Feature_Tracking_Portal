package com.MyProject.Feature_Tracking_Portal.dto.request;

import com.MyProject.Feature_Tracking_Portal.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DocumentRequest {
    private Long featureId;
    private Long userId;
    private DocumentType documentType;
    private String documentLink;
}
