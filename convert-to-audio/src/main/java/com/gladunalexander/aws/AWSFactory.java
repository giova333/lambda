package com.gladunalexander.aws;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Created by Alexander Gladun on 20/05/2018.
 */
public class AWSFactory {

    private static final Regions REGION = Regions.US_EAST_1;

    private static DynamoDB dynamoDb;

    private static AmazonPollyClient amazonPollyClient;

    private static AmazonS3Client amazonS3Client;

    static {
        initDynamoDb();
        initPollyClient();
        initS3Client();
    }

    public static DynamoDB getDynamoDb() {
        return dynamoDb;
    }

    public static AmazonPollyClient getPolly() {
        return amazonPollyClient;
    }

    public static AmazonS3Client getS3() {
        return amazonS3Client;
    }

    private static void initDynamoDb() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        dynamoDb = new DynamoDB(client);
    }

    private static void initPollyClient() {
        amazonPollyClient = new AmazonPollyClient();
        amazonPollyClient.setRegion(Region.getRegion(REGION));
    }

    private static void initS3Client() {
        amazonS3Client = new AmazonS3Client();
    }
}
