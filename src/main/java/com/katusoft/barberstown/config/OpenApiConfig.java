package com.katusoft.barberstown.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("Barberstown API")
                .version("1.0")
                .description("Documentación de la API de Barberstown")
                .contact(new Contact().name("Miguel Olarte").email("mbsic91@gmail.com")));
    }

}
