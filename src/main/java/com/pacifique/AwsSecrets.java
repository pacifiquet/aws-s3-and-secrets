package com.pacifique;

import lombok.Builder;

@Builder
public record AwsSecrets(
        String username,
        String password,
        String host,
        String engine,
        String port,
        String dbInstanceIdentifier) {
}
