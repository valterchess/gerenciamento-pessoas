package br.com.magnasistemas.gerenciamentodepessoas.shared.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DocConfig {
	@Bean
	public OpenAPI testOpenAPIDefinition() {
		String securitySchemeName = "bearerAuth";
		return new OpenAPI().info(new Info().title("Aplicação java para gestão de acesso")
				.contact(new Contact().name("Valter Silva").email("valter.silva.dev@gmail.com"))
				.description("Aplicação em reinicio de desenvolvimento").version("v1.8.1"));
	}

	@Bean
	public GroupedOpenApi testApi() {
		return GroupedOpenApi.builder().group("api-v1.8.1").packagesToScan("br.com.magnasistemas").pathsToMatch("/**")
				.build();
	}
}