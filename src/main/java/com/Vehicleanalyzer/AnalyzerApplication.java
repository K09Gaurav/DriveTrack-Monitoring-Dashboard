package com.Vehicleanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Vehicle Analyzer Spring Boot application.
 * Starts the embedded server and initializes the application context.
 */
@SpringBootApplication
public class AnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyzerApplication.class, args);
	}

}
