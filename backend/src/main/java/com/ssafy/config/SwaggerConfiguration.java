package com.ssafy.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//Swagger-UI 확인
//http://localhost/swagger-ui.html

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info().title("SSAFY WhereIsMyHome API 명세서").description(
			"<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 WhereIsMyHome API<br><img src=\"/assets/favicon.png\" width=\"150\">")
			.version("v1").contact(new io.swagger.v3.oas.models.info.Contact().name("natural-hyeonsu")
				.email("jyjy@ssafy.com").url("http://edu.ssafy.com"));

		return new OpenAPI().components(new Components()).info(info);
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("house-deal").pathsToMatch("/api/house/**").build();
	}

	@Bean
	public GroupedOpenApi memberApi() {
		return GroupedOpenApi.builder().group("member").pathsToMatch("/api/member/**").build();
	}

	@Bean
	public GroupedOpenApi favoriteAreaApi() {
		return GroupedOpenApi.builder().group("favorite-area").pathsToMatch("/api/favorite-area/**").build();
	}

	@Bean
	public GroupedOpenApi dongCode() {
		return GroupedOpenApi.builder().group("dong-code").pathsToMatch("/api/dong-code/**").build();
	}

}