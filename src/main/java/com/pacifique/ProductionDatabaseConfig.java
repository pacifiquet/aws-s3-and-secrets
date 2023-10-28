package com.pacifique;

import lombok.AllArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class ProductionDatabaseConfig {
    private static final String DATABASE = "/booksdb";
    private static final String COLON = ":";
    private static final  String JDBC_POSTGRESQL = "jdbc:postgresql://";
    private final ProjectConfigProperties projectConfigProperties;

    @Bean
    public DataSource dataSource(){
        AwsSecrets secrets = projectConfigProperties.getRDSAWSSecrets();
        if (secrets != null){
            return DataSourceBuilder.create()
                    .url(JDBC_POSTGRESQL +secrets.host()+ COLON +secrets.port()+ DATABASE)
                    .password(secrets.password())
                    .username(secrets.username())
                    .build();
        }
       return null;
    }

}
