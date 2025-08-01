package com.onlineLearningPlatform;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "OnlineLearning App REST API Documentation",
				description = "Online Learning AppREST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Rohith Kolluri",
						email = "rajrohitsunder@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Online Learning App REST API Documentation"
		)
)
public class OnlineLearingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLearingAppApplication.class, args);
	}

}
