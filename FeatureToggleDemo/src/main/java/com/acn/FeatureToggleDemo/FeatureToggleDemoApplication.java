package com.acn.FeatureToggleDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class FeatureToggleDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleDemoApplication.class, args);
	}

}
