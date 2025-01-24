package com.sergiocarvajal.prueba_back_iberpixel.Docs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Libros",
                version = "1.0",
                description = "Prueba tecnica Sergio Carvajal. API con un CRUD de libros"
        )
)
public class SwaggerConfig {
}
