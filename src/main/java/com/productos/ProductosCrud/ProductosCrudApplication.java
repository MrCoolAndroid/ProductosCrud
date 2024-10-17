package com.productos.ProductosCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class ProductosCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductosCrudApplication.class, args);
	}
}
