package com.mn.oglasiBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@EntityScan(basePackages = {"modeli"})
@ComponentScan(basePackages = {"security","controller","repozitorijumi","service", "paypal"})
@EnableJpaRepositories(basePackages = {"repozitorijumi"})
@Configuration
@EnableScheduling
public class OglasiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OglasiBackendApplication.class, args);
	}

}
