package com.plucas.accounts;

import com.plucas.accounts.dto.AccountContactInfoDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef="auditorAwareImpl")
@OpenAPIDefinition(info = @Info(
		title = "Accounts API",
		version = "1.0",
		description = "Documentation Accounts API v1.0"
))
@EnableConfigurationProperties(value = {AccountContactInfoDTO.class})
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
