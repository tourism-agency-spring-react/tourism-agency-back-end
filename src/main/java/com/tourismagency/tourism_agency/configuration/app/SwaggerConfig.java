package com.tourismagency.tourism_agency.configuration.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API Tourism Agency",
                description = "An API for a tourism agency that offers individual and packaged tourist services.",
                termsOfService = "www.tourism-agency-api.com/terminals_and_services",
                version = "1.0.0",
                contact = @Contact(
                        name = "Israel",
                        url = "www.tourism-agency-api.com",
                        email = "israel.bastion123@gmail.com"
                ),
                license = @License(
                        name = "Standard Software use License for me",
                        url = "www.tourism-agency-api.com/license"
                )
        ),
        servers = {
                @Server(
                        description = "dev server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "prod server",
                        url = "https://tourism-agency-api:8080"
                ),
        }
)
public class SwaggerConfig {


}
