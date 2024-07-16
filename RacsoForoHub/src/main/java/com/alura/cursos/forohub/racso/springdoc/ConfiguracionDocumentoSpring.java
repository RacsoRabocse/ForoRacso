package com.alura.cursos.forohub.racso.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionDocumentoSpring {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("Forum Hub API")
        .version("1.0")
        .description("**The Forum Hub API** is a platform that allows users to create and participate in discussions on various topics. Users can create new topics, post answers to existing topics, and mark answers as resolved if they provide a satisfactory solution.\n\n" +
          "To access the API, users must first create an account and authenticate themselves by obtaining a valid JWT token. This token must be included in the `Authorization` header of all subsequent requests to the API.\n\n" +
          "The API provides endpoints for the following functionalities:\n\n" +
          "- **Usuario management**: Users can register, login, and update their profile information.\n" +
          "- **Tema management**: Users can create new topics, view existing topics, and post answers to topics.\n" +
          "- **Respuesta management**: Users can post answers to topics, mark answers as resolved, and update or delete their own answers.\n" +
          "- **Notification management**: Users can receive notifications when their topics receive new answers or when their answers are marked as resolved.\n\n" +
          "The API is built using *Spring Boot 3* and utilizes various Spring components, such as *Spring Security* for authentication and authorization, *Spring Data* for database interactions, and *SpringDoc* for generating interactive API documentation."))
      .components(new Components()
        .addSecuritySchemes("bearer-key",
          new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
  }

  @Bean
  public String message() {
    return "bearer is worked";
  }
}
