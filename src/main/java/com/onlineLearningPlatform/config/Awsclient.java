package com.onlineLearningPlatform.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Awsclient {

    @Value("${cloud.aws.credentials.accesskey}")
    private String accesskey;

    @Value("${cloud.aws.credentials.secretkey}")
    private String secretkey;

    @Value("${cloud.aws.region.static}")
    private String region;


    @Bean
    public AmazonS3 amazonS3client(){
        AWSCredentials awsCredentials = new BasicAWSCredentials(accesskey,secretkey);
        return AmazonS3ClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(awsCredentials)
        ).withRegion(Regions.US_EAST_1).build();
    }

}
