package com.productos.ProductosCrud.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Autowired(required = false)
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI customOpenAPI() {
        String title = (buildProperties != null) ? String.format("%s.%s", buildProperties.getGroup(), buildProperties.getName()) : "ProductosCrud API";
        String version = (buildProperties != null) ? buildProperties.getVersion() : "1.0.0";
        String description = (buildProperties != null) ? buildProperties.get("description") : "API para la gestión de productos.";
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description)
                        .contact(new Contact().name("Equipo de Desarrollo").email("pabloreinoso1@live.com"))
                .license(new
                        License().name("ISSD").url("https://issd.edu.ar/")));
    }
}
