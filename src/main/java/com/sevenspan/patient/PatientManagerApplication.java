package com.sevenspan.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class PatientManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientManagerApplication.class, args);
	}

}
