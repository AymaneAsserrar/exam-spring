package com.bikeshop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Bike Shop API", version = "1.0", description = "API documentation for Bike Shop"),
    security = @SecurityRequirement(name = "Authentication")
)
@SecurityScheme(
    name = "Authentication",
    type = SecuritySchemeType.APIKEY,
    in = io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER,
    paramName = "Authentication"
)
public class OpenApiConfig {
    // No implementation needed, annotations configure OpenAPI
}
