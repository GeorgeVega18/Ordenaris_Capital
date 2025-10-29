package com.ordenariscapital.riskengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ordenariscapital.riskengine.repository")
@EntityScan(basePackages = "com.ordenariscapital.riskengine.entity")
public class RiskengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskengineApplication.class, args);
	}

}
