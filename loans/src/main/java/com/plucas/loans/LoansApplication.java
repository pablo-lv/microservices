package com.plucas.loans;

import com.plucas.loans.dto.LoansInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.plucas.loans.controller") })
@EnableJpaRepositories("com.plucas.loans.repository")
@EntityScan("com.plucas.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "plucas Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Madan Reddy",
						email = "tutor@plucas.com",
						url = "https://www.plucas.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.plucas.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "plucas Loans microservice REST API Documentation",
				url = "https://www.plucas.com/swagger-ui.html"
		)
)
@EnableConfigurationProperties({LoansInfoDTO.class})
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}
