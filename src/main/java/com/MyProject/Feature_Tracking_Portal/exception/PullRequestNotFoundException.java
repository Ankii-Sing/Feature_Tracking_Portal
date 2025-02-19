package com.MyProject.Feature_Tracking_Portal.exception;

public class PullRequestNotFoundException extends RuntimeException {
    public PullRequestNotFoundException(String message) {
        super(message);
    }
}
