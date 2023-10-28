package com.pacifique;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Pacifique Twagirayesu",
                        email = "pacifiquetwagirayesu@gmail.com"
                ),
                description = "API documentation for books demo and storage s3 bucket",
                title = "BOOKS DEMO AND S3 BUCKET",
                version = "1.0"
        )
)
public record OpenApiDocumentationConfig() {
}
