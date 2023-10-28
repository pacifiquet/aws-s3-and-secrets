package com.pacifique;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@AllArgsConstructor
@Hidden
public class AppController {
    private final ProjectConfigProperties configProperties;
    @GetMapping
    ResponseEntity<Map<Object, Object>> home(){
        Invoice invoice = new Invoice("123", 45);
        AwsSecrets awsSecrets = configProperties.getRDSAWSSecrets();
        SenderGrid senderGrid = configProperties.getSenderGridApiKey();

        if (awsSecrets == null || senderGrid == null ){
            return null;
        }

        return ResponseEntity.ok(Map.of(
                "name",configProperties.name(),
                "key", configProperties.key(),
                "url", configProperties.url(),
                "endpoint", List.of(configProperties.endpointUrl(),configProperties.bucketDemo()),
                "password", configProperties.password(),
                "access-key",configProperties.accessKey(),
                "secret-key",configProperties.secretKey(),
                "send-grid-email-key",senderGrid.senderGridApiKey(),
                "invoice", Map.of("id",invoice.id(),"number",invoice.number()),
                "aws-db-config",Map.of("secrets",awsSecrets)
        ));
    }
}
