package edu.ifgoiano.trabalho.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Cicloshop",
            description = "Uma API para a gerência de oficinas de bicicletas.",
            license = @License(name = "Licença MIT", url = "http://mit-license.org/"),
            version = "1.0"),
    servers = @Server(url = "http://localhost:8080", description = "Desenvolvimento"))
public class OpenApiConfig {}
