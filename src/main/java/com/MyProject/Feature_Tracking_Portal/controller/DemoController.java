package com.MyProject.Feature_Tracking_Portal.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/demo")
public class DemoController {
    @GetMapping("/home")
    public String testEndpoint() {
        return "Application is running fine!";
    }
}
