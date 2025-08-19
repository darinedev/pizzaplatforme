package com.darine.pizzaplatforme.pizzaplatforme.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Pizzeria")
                        .version("1.0.0")
                        .description("API REST pour la gestion d'une pizzeria")
                        .contact(new Contact()
                                .name("Support Pizzeria")
                                .email("support@pizzeria.com")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Serveur de développement"),
                        new Server().url("https://api.pizzeria.com").description("Serveur de production")
                ));
    }
}