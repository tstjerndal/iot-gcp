package com.starvalley.iotserver.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;
import javax.persistence.Id;

@SpringBootApplication
@EnableJpaAuditing
public class IotApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);

	}

}

