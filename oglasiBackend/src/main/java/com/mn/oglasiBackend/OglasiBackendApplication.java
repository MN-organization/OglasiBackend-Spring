package com.mn.oglasiBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"modeli"})
public class OglasiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OglasiBackendApplication.class, args);
	}

}
