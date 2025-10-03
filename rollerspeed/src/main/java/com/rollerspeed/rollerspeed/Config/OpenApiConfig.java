package com.rollerspeed.rollerspeed.Config;


import io.swagger.v3.oas.models.OpenAPI; // Importación de la clase OpenAPI de la especificación OpenAPI 3
import io.swagger.v3.oas.models.info.Info; // Importación de la clase Info utilizada para describir la API
import org.springframework.context.annotation.Bean; // Importación de la anotación @Bean para definir un bean de Spring
import org.springframework.context.annotation.Configuration; // Importación de la anotación @Configuration para definir una clase de configuración de Spring

/**
 * Clase de configuración para la documentación de la API utilizando OpenAPI.
 * Esta clase configura un bean de OpenAPI con la información de la API.
 */
@Configuration
public class OpenApiConfig {

    @Bean 
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info() // Crea una nueva instancia de Info para describir la API
                    .title("Escuela de patinaje API") // Establece el título de la API
                        .version("1.0") // Establece la versión de la API
                        .description("API para gestionar la escuela de patinaje") // Establece la descripción de la API
                ); 
    }
}