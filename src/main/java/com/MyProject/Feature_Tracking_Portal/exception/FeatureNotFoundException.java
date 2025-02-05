package com.MyProject.Feature_Tracking_Portal.exception;

public class FeatureNotFoundException extends RuntimeException {
    public FeatureNotFoundException(String message) {
        super(message);
    }
}
