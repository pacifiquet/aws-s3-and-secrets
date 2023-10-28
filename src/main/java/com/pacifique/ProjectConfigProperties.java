package com.pacifique;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClient;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.Gson;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


@ConfigurationProperties("project")
public record ProjectConfigProperties(
        String accessKey,
        String bucketDemo,
        String senderGridEmailKey,
        String secretKey,
        String senderGridSecretName,
        String databaseSecretName,
        String name,
        String key,
        String url,
        String secretAws,
        String password,
        String endpointUrl,
        Map<Object,Object> account) {
    static String secret;
    static String senderGrid;
    static Gson gson = new Gson();
    static Region region = Region.getRegion(Regions.AP_SOUTHEAST_1);
    static GetSecretValueResult getSecretValueResult =  null;
    //
    public AwsSecrets getRDSAWSSecrets(){
        AWSSecretsManager client = AWSSecretsManagerClient.builder()
                .withRegion(region.getName())
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretKey)))
                .build();
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(this.databaseSecretName);
        getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        if (getSecretValueResult.getSecretString() != null){
            secret = getSecretValueResult.getSecretString();
            return gson.fromJson(secret, AwsSecrets.class);
        }
        return null;
    }


    public SenderGrid getSenderGridApiKey(){
        AWSSecretsManager client = AWSSecretsManagerClient.builder()
                .withRegion(region.getName())
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretKey)))
                .build();

        GetSecretValueRequest senderGridKey = new GetSecretValueRequest().withSecretId(this.senderGridSecretName);
        getSecretValueResult = client.getSecretValue(senderGridKey);

        if (getSecretValueResult.getSecretString() != null){
            senderGrid = getSecretValueResult.getSecretString();
            return gson.fromJson(senderGrid, SenderGrid.class);
        }
        return null;
    }


}
