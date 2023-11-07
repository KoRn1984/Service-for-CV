package by.krainet.matveenko.serviceforcv.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Service for CV Api",
                description = "Service for CV", version = "2.0.2",
                contact = @Contact(
                        name = "Matveenko Yury",
                        email = "korn1984@narod.ru",
                        url = "https://github.com/KoRn1984"
                )
        )
)
public class OpenApiConfig {

}