package com.ezpz.ecom.ezstore.inventory.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfig {
//	 @Bean
//	    public OpenAPI publicApi() {
//	        return new O.builder()
//	                .group("public-api") // Group name for API
//	                .pathsToMatch("/product/**")  // You can match specific paths
//	                .build();
//	    }

	    @Bean
	    OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("E-Commerce inventory API")
	                        .version("1.0")
	                        .description("This is the API documentation for the E-Commerce application.")
	                );
	    }
}
