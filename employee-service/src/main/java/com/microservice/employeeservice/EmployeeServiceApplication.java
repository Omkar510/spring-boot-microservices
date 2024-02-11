package com.microservice.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
	info = @Info(
		title = "Employee Service REST APIs",
		description = "Employee Service REST APIs Documentation",
		version = "v1",
		contact = @Contact(
			name = "Omkar",
			email = "omkarsuvare49@gmai.com",
			url = "http://www.employee-service.com"
		),
		license = @License(
			name = "Apache",
			url = "http://www.employee-service.com"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Employee Service Doc",
		url = "http://www.employee-service.com"
	)
)
@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication {

    // @Bean
    // RestTemplate restTemplate() {
	// 	return new RestTemplate();
	// }

	@Bean
	WebClient webClient() {
		return WebClient.builder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
