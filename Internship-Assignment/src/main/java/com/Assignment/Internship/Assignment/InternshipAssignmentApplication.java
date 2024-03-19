package com.Assignment.Internship.Assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class InternshipAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternshipAssignmentApplication.class, args);
	}

}
