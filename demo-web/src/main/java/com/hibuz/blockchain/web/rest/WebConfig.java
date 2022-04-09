package com.hibuz.blockchain.web.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public OpenAPI openApi() {
		return new OpenAPI().info(new Info().title("Blockchain Demo")
				.description("blockchain demo web application").version("v0.0.1"));
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		 registry.addViewController( "/" ).setViewName( "redirect:/swagger-ui/index.html" );
	}
}
