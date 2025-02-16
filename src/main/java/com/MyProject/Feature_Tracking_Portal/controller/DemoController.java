package com.MyProject.Feature_Tracking_Portal.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class DemoController {
    @GetMapping("/demo")
    public ResponseEntity<String> demo(@RequestHeader("Authorization") String token) {
        System.out.println("token is : " + token);
        return ResponseEntity.ok("Hello World");
    }
}
