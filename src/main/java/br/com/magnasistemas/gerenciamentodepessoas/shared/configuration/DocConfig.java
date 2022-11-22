package br.com.magnasistemas.gerenciamentodepessoas.shared.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DocConfig {
	@Bean
	public OpenAPI testOpenAPIDefinition() {
        String securitySchemeName = "bearerAuth";
		return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(new Components().addSecuritySchemes(securitySchemeName,
						new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("bearer")
								.bearerFormat("JWT")))
				.info(new Info().title("Aplicação java para gestão de acesso")
						.contact(new Contact().name("Valter Silva").email("valter.silva.dev@gmail.com"))
						.description("Aplicação desenvolvida para testes.").version("v1.2.0"));
	}

	@Bean
	public GroupedOpenApi testApi() {
		return GroupedOpenApi.builder().group("api-v1.2").packagesToScan("br.com.magnasistemas")
				.pathsToMatch("/**").build();
	}
}