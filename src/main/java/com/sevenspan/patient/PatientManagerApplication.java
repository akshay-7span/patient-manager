package com.sevenspan.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PatientManagerApplication {

//  This will stop automatic migration by flyway
//	@Bean
//	public FlywayMigrationStrategy flywayMigrationStrategy(){
//		return args -> {};
//	}

    public static void main(String[] args) {
        SpringApplication.run(PatientManagerApplication.class, args);
    }

}
