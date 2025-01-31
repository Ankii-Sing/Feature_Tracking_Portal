package com.MyProject.Feature_Tracking_Portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FeatureTrackingPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeatureTrackingPortalApplication.class, args);

	}

}
