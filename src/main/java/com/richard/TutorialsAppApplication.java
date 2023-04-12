package com.richard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.richard.infrastructure.persistence.repositories")
@SpringBootApplication(scanBasePackages = "com.richard")
public class TutorialsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialsAppApplication.class, args);
	}

}
