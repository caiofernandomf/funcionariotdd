package io.linuxtips.funcionariotdd.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDbEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAwsAcessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAwsSecretKey;

    @Bean
    AmazonDynamoDB amazonDynamoDB(){
        AwsClientBuilder.EndpointConfiguration endpointConfiguration =
                new AwsClientBuilder.EndpointConfiguration(amazonDynamoDbEndpoint,"us-east-1");

        return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(endpointConfiguration).build();
    }

    @Bean
    AWSCredentials awsCredentials(){
        return new BasicAWSCredentials(amazonAwsAcessKey,amazonAwsSecretKey);
    }
}
