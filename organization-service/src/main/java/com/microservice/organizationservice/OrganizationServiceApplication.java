package com.microservice.organizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
	info = @Info(
		title = "Organization Service REST APIs",
		description = "Organization Service REST APIs Documentation",
		version = "v1",
		contact = @Contact(
			name = "Omkar",
			email = "omkarsuvare49@gmail.com",
			url = "http://www.organization-service.com"
		),
		license = @License(
			name = "Apache 2.0",
			url = "http://www.organization-service.com"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Organization Service Doc",
		url = "http://www.organization-service.com"
	)
)
@SpringBootApplication
public class OrganizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}
