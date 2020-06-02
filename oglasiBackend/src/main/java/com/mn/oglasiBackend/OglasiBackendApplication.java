package com.mn.oglasiBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"modeli"})
@ComponentScan(basePackages = {"security","controller","repozitorijumi","service"})
@EnableJpaRepositories(basePackages = {"repozitorijumi"})
public class OglasiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OglasiBackendApplication.class, args);
	}

}
