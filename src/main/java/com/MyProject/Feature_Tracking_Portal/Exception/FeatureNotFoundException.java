package com.MyProject.Feature_Tracking_Portal.Exception;

public class FeatureNotFoundException extends RuntimeException {
    public FeatureNotFoundException(String message) {
        super(message);
    }
}
