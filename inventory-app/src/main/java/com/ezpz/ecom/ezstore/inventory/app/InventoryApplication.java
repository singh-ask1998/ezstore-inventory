package com.ezpz.ecom.ezstore.inventory.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.ezpz.ecom.ezstore.inventory.app.config.OpenAPIConfig;


@SpringBootApplication
@EnableDiscoveryClient
@Import(OpenAPIConfig.class)
@EnableJpaAuditing
public class InventoryApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
}
